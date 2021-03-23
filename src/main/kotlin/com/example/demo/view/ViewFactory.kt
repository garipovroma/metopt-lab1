package com.example.demo.view

import com.example.demo.controller.BaseViewIterator

interface ViewFactory {
    fun view(eps: Double): GraphView
}