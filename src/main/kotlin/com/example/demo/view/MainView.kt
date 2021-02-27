package com.example.demo.view

import com.example.demo.controller.DichotomyViewIterator
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import tornadofx.*
import java.util.*

class SelectionMethodEvent(val iter: DichotomyViewIterator) : FXEvent()

class NextIterationEvent() : FXEvent()


class MainView : View("Hello TornadoFX") {
    val controller: MethodController by inject()
    var iterator: DichotomyViewIterator? = null
    override val root = vbox {
        combobox(values = controller.methods) {
            setOnAction {
                fire(SelectionMethodEvent(selectionModel.selectedItem))
            }
        }
        subscribe<SelectionMethodEvent> {
            iterator = it.iter
            fire(NextIterationEvent())
            linechart(iterator.toString(), NumberAxis(0.0, 100.0, 10.0), NumberAxis()) {
                subscribe<NextIterationEvent> {
                    if (iterator == null || !iterator!!.hasNext()) {

                    } else {
                        xAxis
                        data.clear()
                        iterator!!.next().forEach {
                            series(it.toString()) {
                                it.points.forEach {
                                    data(it.x, it.y)
                                }
                            }
                        }
                    }
                }
            }
        }
        button("next") {
            setOnAction {
                fire(NextIterationEvent())
            }
        }
    }
}

class MethodController: Controller() {
    val methods = listOf(DichotomyViewIterator(0.0, Math.PI * 2.0, 1e-9, 1e-10))
}