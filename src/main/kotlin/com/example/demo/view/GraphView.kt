package com.example.demo.view

import com.example.demo.controller.ViewIterator
import com.example.demo.model.base.Graph

class GraphView(private val viewIterator: ViewIterator, private val name: String) {
    private val graphs = mutableListOf<List<Graph>>()
    private var cur = -1
    private val count: Int
    init {
        count = viewIterator.iterationsCount
        println(count)
        while (viewIterator.hasNext()) {
            graphs.add(viewIterator.next())
        }
    }

    override fun toString(): String = run { name }

    fun next(): List<Graph> {
        return graphs[++cur]
    }

    fun prev(): List<Graph> {
        return graphs[--cur]
    }

    fun hasNext(): Boolean {
        return cur + 1 < count
    }

    fun hasPrev(): Boolean {
        return cur > 0
    }
}