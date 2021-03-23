package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.*
import com.example.demo.model.base.Graph
import com.example.demo.model.base.Point
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.layout.VBox
import tornadofx.*
import java.util.*
import kotlin.math.exp
import kotlin.math.sin

class SelectionMethodEvent(val factory: ViewFactory) : FXEvent()

class NextIterationEvent(val view: GraphView) : FXEvent()

class PrevIterationEvent(val view: GraphView) : FXEvent()

class MainView : View("Optimization methods") {
    val controller: MethodController by inject()
    var eps: Double = 1e-3
    fun getChart(graphs: List<Graph>): LineChart<Number, Number>.() -> Unit = {
        graphs.map { graph ->
            animated = false
            val series = series(graph.toString()) {
                graph.points.forEach {
                    data(it.x, it.y)
                }
            }
            series.data.forEach {
                it.node.lookup(".chart-line-symbol").style =
                    if (graph.points.size == 1) "-fx-padding: 6px;" else "-fx-padding: 1px;"
            }
        }
    }

    private fun NumberAxis.setXAxis(graphs: List<Graph>) {
        val left = graphs.foldRight<Graph, Double>(100.0) { el, acc ->
            val min = el.points.foldRight<Point, Double>(100.0) { point, pAcc ->
                if (point.x < pAcc) point.x else pAcc
            }
            if (min < acc) min else acc
        }
        val right = graphs.foldRight<Graph, Double>(-100.0) { el, acc ->
            val max = el.points.foldRight<Point, Double>(-100.0) { point, pacc ->
                if (point.x > pacc) point.x else pacc
            }
            if (max > acc) max else acc
        }
        lowerBound = left
        upperBound = right
        tickUnit = (right - left) / 10.0
    }

    private fun NumberAxis.setYAxis(graphs: List<Graph>) {
        val left = graphs.foldRight(100.0) { el, acc ->
            val min = el.points.foldRight<Point, Double>(100.0) { point, pacc ->
                if (point.y < pacc) point.y else pacc
            }
            if (min < acc) min else acc
        }
        val right = graphs.foldRight(-100.0) { el, acc ->
            val max = el.points.foldRight<Point, Double>(-100.0) { point, pacc ->
                if (point.y > pacc) point.y else pacc
            }
            if (max > acc) max else acc
        }
        lowerBound = left
        upperBound = right
        tickUnit = (right - left) / 10.0
    }

    private fun assignAxis(axis: NumberAxis, otherAxis: NumberAxis): Unit {
        axis.lowerBound = otherAxis.lowerBound
        axis.upperBound = otherAxis.upperBound
        axis.tickUnit = otherAxis.tickUnit
    }

    override val root = hbox {
        addClass(Styles.chartBox)
        vbox {
            id = "vbox"
            combobox(values = controller.methods) {
                setOnAction {
                    fire(SelectionMethodEvent(selectionModel.selectedItem))
                }
            }
            hbox {
                id = "mainBox"
            }
        }
        val xAxis = NumberAxis()
        xAxis.isForceZeroInRange = false
        val yAxis = NumberAxis()
        yAxis.isForceZeroInRange = false
        val chart = linechart("none", xAxis, yAxis) {
            id = "method-chart"
            addClass(Styles.chartBox)
        }
        subscribe<SelectionMethodEvent> {
            val factory = it.factory.view(eps)
            val node = (this@hbox.children.find {node -> node.id == "vbox"} as VBox)
                .children.find {node -> node.id == "mainBox"}
            node!!.replaceWith(hbox {
                id = "mainBox"
                button("next") {
                    style = "-fx-margin: 5px"
                    action {
                        fire(NextIterationEvent(factory))
                    }
                }
                button("prev") {
                    style = "-fx-margin: 5px"
                    action {
                        fire(PrevIterationEvent(factory))
                    }
                }
            })
            fire(NextIterationEvent(factory))
        }
        subscribe<NextIterationEvent> {
            with (it) {
                if (view.hasNext()) {
                    chart.title = view.toString()
                    chart.data.clear()
                    val graphs = view.next()
                    xAxis.setXAxis(graphs)
                    yAxis.setYAxis(graphs)
                    chart.(getChart(graphs))()
                    this@hbox.add(chart)
                }
            }
        }
        subscribe<PrevIterationEvent> {
            with (it) {
                if (view.hasPrev()) {
                    chart.title = view.toString()
                    chart.data.clear()
                    val graphs = view.prev()
                    xAxis.setXAxis(graphs)
                    yAxis.setYAxis(graphs)
                    chart.(getChart(graphs))()
                    this@hbox.add(chart)
                }
            }
        }
    }
}

class MethodController: Controller() {
    val func: (Double) -> Double = { x: Double -> -3.0 * x * sin(0.75 * x) + exp(-2.0 * x) }
    val methods =
        listOf(
            object : ViewFactory {
                override fun view(eps: Double) =
                    GraphView(DichotomyViewIterator(0.0, Math.PI * 2.0, eps, eps / 10.0, func),
                        "Dichotomy")
                override fun toString(): String = "Dichotomy"
            },
            object : ViewFactory {
                override fun view(eps: Double) =
                    GraphView(GoldenRationViewIterator(0.0, Math.PI * 2.0, eps, func),
                        "Golden Ratio")
                override fun toString(): String = "Golden Ratio"
            },
            object : ViewFactory {
                override fun view(eps: Double) =
                    GraphView(ParabolaViewIterator(0.0, Math.PI * 2.0, eps, func),
                        "Parabola")
                override fun toString(): String = "Parabola"
            },
            object : ViewFactory {
                override fun view(eps: Double) =
                    GraphView(FibonacciViewIterator(0.0, Math.PI * 2.0, eps, func),
                        "Fibonacci")
                override fun toString(): String = "Fibonacci"
            },
            object : ViewFactory {
                override fun view(eps: Double) =
                    GraphView(BrentViewIterator(0.0, Math.PI * 2.0, eps, func),
                        "Brent")
                override fun toString(): String = "Brent"
            }
        )
}