package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val chartBox by cssclass()
    }

    init {
        root {
            minWidth = 1400.px
            minHeight = 700.px
        }
        root {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        chartBox {
            minWidth = 1000.px
        }
        chartLineSymbol {
            padding = box(1.px)
//            backgroundRadius = multi(box(100.px))
//            backgroundColor = multi(Color.RED)
        }
    }
}