package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.DichotomyViewIterator
import com.example.demo.controller.GoldenRationViewIterator
import com.example.demo.controller.ParabolaViewIterator
import com.example.demo.controller.ViewIterator
import com.example.demo.model.base.Graph
import com.example.demo.model.base.Point
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import tornadofx.*
import java.util.*

class SelectionMethodEvent(val iter: ViewIterator) : FXEvent()

class NextIterationEvent(val graphs: List<Graph>) : FXEvent()

class MainView : View("huy TornadoFX") {
    val controller: MethodController by inject()
    var iterator: ViewIterator? = null

    fun getChart(graphs: List<Graph>): LineChart<Number, Number>.() -> Unit = {
        graphs.map {
            series(it.toString()) {
                it.points.forEach {
                    data(it.x, it.y)
                }
            }
        }
    }

    fun getXAxis(graphs: List<Graph>): NumberAxis = run {
        val left = graphs.foldRight<Graph, Double>(100.0) { el, acc ->
            val min = el.points.foldRight<Point, Double>(100.0) { point, pacc ->
                if (point.x < pacc) point.x else pacc
            }
            if (min < acc) min else acc
        }
        val right = graphs.foldRight<Graph, Double>(-100.0) { el, acc ->
            val max = el.points.foldRight<Point, Double>(-100.0) { point, pacc ->
                if (point.x > pacc) point.x else pacc
            }
            if (max > acc) max else acc
        }
        NumberAxis(left, right, (right - left) / 15.0)
    }

    fun getYAxis(graphs: List<Graph>): NumberAxis = run {
        val left = graphs.foldRight<Graph, Double>(100.0) { el, acc ->
            val min = el.points.foldRight<Point, Double>(100.0) { point, pacc ->
                if (point.y < pacc) point.y else pacc
            }
            if (min < acc) min else acc
        }
        val right = graphs.foldRight<Graph, Double>(-100.0) { el, acc ->
            val max = el.points.foldRight<Point, Double>(-100.0) { point, pacc ->
                if (point.y > pacc) point.y else pacc
            }
            if (max > acc) max else acc
        }
        NumberAxis(left, right, (right - left) / 15.0)
    }

    override val root = hbox {
        addClass(Styles.chartBox)
        combobox(values = controller.methods) {
            setOnAction {
                fire(SelectionMethodEvent(selectionModel.selectedItem))
            }
        }
        linechart("none", NumberAxis(), NumberAxis()) {
            id = "hueta"
            addClass(Styles.chartBox)
        }
        subscribe<SelectionMethodEvent> {
            iterator = it.iter
            if (iterator!!.hasNext()) {
                fire(NextIterationEvent(iterator!!.next()))
            }
        }
        button("next") {
            action {
                fire(NextIterationEvent(iterator!!.next()))
            }
        }
        subscribe<NextIterationEvent> {
            this@hbox.children.asSequence().filter { it.id == "hueta" }.toList()[0].removeFromParent()
            val chart = linechart(iterator.toString(), getXAxis(it.graphs), getYAxis(it.graphs)) {
                id = "hueta"
                addClass(Styles.chartBox)
            }
            chart.(getChart(it.graphs))()
            this@hbox.add(chart)
        }
    }
}

class MethodController: Controller() {
    val methods = listOf(DichotomyViewIterator(0.0, Math.PI * 2.0, 1e-5, 1e-6), GoldenRationViewIterator(0.0, Math.PI * 2.0, 1e-5, 1e-6), ParabolaViewIterator(0.0, Math.PI * 2.0, 1e-5))
}