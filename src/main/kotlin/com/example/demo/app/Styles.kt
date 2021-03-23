package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {

    companion object {
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
        button {
            endMargin = 4.px
            startMargin = 4.px
        }
    }
}