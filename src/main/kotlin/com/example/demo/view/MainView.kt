package com.example.demo.view

import com.example.demo.controller.DichotomyViewIterator
import com.example.demo.controller.ParabolaViewIterator
import com.example.demo.controller.ViewIterator
import com.example.demo.model.base.Graph
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import tornadofx.*
import java.util.*

class SelectionMethodEvent(val iter: ViewIterator) : FXEvent()

class NextIterationEvent(val graphs: List<Graph>) : FXEvent()

class GraphView() : Fragment() {
    override val root = hbox {
    }
    fun getChart(graphs: List<Graph>): LineChart<Number, Number> = run {
        var xmax = -1e9
        var xmin = 1e9
        var ymax = -1e9
        var ymin = 1e9
        graphs.forEach {
            it.points.forEach {
                xmax = Math.max(xmax, it.x)
                xmin = Math.min(xmin, it.x)
                ymax = Math.max(ymax, it.y)
                ymin = Math.min(ymin, it.y)
            }
        }
        linechart(graphs.toString(), NumberAxis(xmin, xmax,(xmax - xmin) / 10.0), NumberAxis(ymin, ymax, (ymax - ymin) / 10.0)) {
            graphs.forEach {
                series(it.toString()) {
                    it.points.forEach {
                        data(it.x, it.y)
                    }
                }
            }
        }
    }
}

class MainView : View("Hello TornadoFX") {
    val controller: MethodController by inject()
    var iterator: ViewIterator? = null
    var graphView = GraphView()
    override val root = vbox {
        combobox(values = controller.methods) {
            setOnAction {
                fire(SelectionMethodEvent(selectionModel.selectedItem))
            }
        }
        add(graphView)
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
            println(it.graphs)
            graphView.root.clear()
            val chart = graphView.getChart(it.graphs)
            chart.createSymbols = false
            graphView.root.add(
                hbox {
                    add(chart)
                }
            )
        }
    }
}

class MethodController: Controller() {
    val methods = listOf(DichotomyViewIterator(0.0, Math.PI * 2.0, 1e-5, 1e-6), ParabolaViewIterator(0.0, Math.PI * 2.0, 1e-5))
}