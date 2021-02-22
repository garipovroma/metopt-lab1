package com.example.demo.view

import com.example.demo.app.Styles
import javafx.beans.property.SimpleStringProperty
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.ValueAxis
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val controller: MyController by inject()
    val input = SimpleStringProperty()
    val length = Math.PI
    val step = length / 1000.0;
    fun fuktion(x: Double): Double { return 3 * x * Math.sin(0.75 * x) + Math.exp(-2 * x) }
    override val root = vbox {
        linechart("3x * sin(0.75x) + e^(-2x)", NumberAxis(), NumberAxis()) {
            series("KAL") {
                var x = 0.0
                for (i in 1..1000) {
                    data(x, fuktion(x))
                    x += step
                }
            }
        }
//        form {
//            fieldset {
//                field("Input") {
//                    textfield(input)
//                }
//
//                button("Commit") {
//                    action {
//                        controller.writeToDb(input.value)
//                        input.value = ""
//                    }
//                }
//            }
//        }
    }
}

class MyController: Controller() {
    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }
}