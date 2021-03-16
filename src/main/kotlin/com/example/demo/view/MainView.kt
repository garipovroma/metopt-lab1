package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.*
import com.example.demo.model.base.Graph
import com.example.demo.model.base.Point
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import tornadofx.*
import java.util.*

class SelectionMethodEvent(val factory: ViewFactory) : FXEvent()

class NextIterationEvent(val graphs: List<Graph>) : FXEvent()

class MainView : View("huy TornadoFX") {
    val controller: MethodController by inject()
    var iterator: ViewIterator? = null

    fun getChart(graphs: List<Graph>): LineChart<Number, Number>.() -> Unit = {
        graphs.map {
            animated = false
            series(it.toString()) {
                if (it.points.size == 1) {
                    val point = it.points[0]
                    val chartData = data(point.x, point.y)
                    chartData.node = Circle(5.0)
                } else {
                    it.points.forEach {
                        data(it.x, it.y).node = Rectangle(0.0, 0.0)
                    }
                }
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
        tickUnit = (right - left) / 100.0
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
            combobox(values = controller.methods) {
                setOnAction {
                    fire(SelectionMethodEvent(selectionModel.selectedItem))
                }
            }
            button("next") {
                action {
                    fire(NextIterationEvent(iterator!!.next()))
                }
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
            iterator = it.factory.viewIterator()
            if (iterator!!.hasNext()) {
                fire(NextIterationEvent(iterator!!.next()))
            }
        }
        subscribe<NextIterationEvent> {
            chart.title = iterator.toString()
            chart.data.clear()
            xAxis.setXAxis(it.graphs)
            yAxis.setYAxis(it.graphs)
            chart.(getChart(it.graphs))()
            this@hbox.add(chart)
        }
    }
}

class MethodController: Controller() {
    val methods =
        listOf(
            object : ViewFactory {
                override fun viewIterator() =
                    DichotomyViewIterator(0.0, Math.PI * 2.0, 1e-3, 1e-5)
                override fun toString(): String = "Dichotomy"
            },
            object : ViewFactory {
                override fun viewIterator() =
                    GoldenRationViewIterator(0.0, Math.PI * 2.0, 1e-3, 1e-5)
                override fun toString(): String = "Golden Ration"
            },
            object : ViewFactory {
                override fun viewIterator() =
                    ParabolaViewIterator(0.0, Math.PI * 2.0, 1e-3)
                override fun toString(): String = "Parabola"
            },
            object : ViewFactory {
                override fun viewIterator() =
                    FibonacciViewIterator(0.0, Math.PI * 2.0, 1e-3)
                override fun toString(): String = "Fibonacci"
            }
        )
}