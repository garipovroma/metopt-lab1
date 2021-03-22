package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.OptimizationMethodIteration;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewIterator implements ViewIterator {
    protected double currentIteration;
    protected double iterationsCount;
    protected final double left;
    protected final double right;
    protected BaseViewIterator(double left, double right) {
        this.right = right;
        this.left = left;
        currentIteration = 0;
    }
    protected void addSinglePointGraph(List<Graph> graphs, Point point, String message) {
        List<Point> single = new ArrayList<>();
        single.add(point);
        graphs.add(new Graph(single, message));
    }

    protected double apply(double x) {
        return getIteration().getFunction().apply(x);
    }

    public abstract OptimizationMethodIteration getIteration();
}
