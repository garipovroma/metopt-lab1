package com.example.demo.model.iterations;

import com.example.demo.model.base.Graph;
import com.example.demo.model.optimizations.Dichotomy;

import java.util.List;

public class DichotomyIterator {
    private final Dichotomy dichotomy;
    private List<Graph> graph;

    public DichotomyIterator(Dichotomy dichotomy) {
        this.dichotomy = dichotomy;
    }

    public DichotomyIterator next() {
        return this;
    }

    public boolean hasNext() {
        return false;
    }

    public Dichotomy getDichotomy() {
        return dichotomy;
    }
}
