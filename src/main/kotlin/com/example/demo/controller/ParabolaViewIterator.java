package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.iterations.ParabolaIteration;

import java.util.ArrayList;
import java.util.List;

public class ParabolaViewIterator extends BaseViewIterator{
    private ParabolaIteration parabolaIteration;
    private final Point extremum;
    private final double left;
    private final double right;
    public ParabolaViewIterator(double left, double right, double eps, DoubleFunction function) {
        this.extremum = new Point(1.0, 1.0);
        // :TODO: assign smth
        this.left = left;
        this.right = right;
        this.parabolaIteration =
            new ParabolaIteration(left, right, eps, function);
    }
    @Override
    public boolean hasNext() {
        return parabolaIteration.hasNext();
    }

    @Override
    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
//        double left = parabolaIteration.getLeft();
//        double right = parabolaIteration.getRight();
        res.add(Graph.intervalCount(
                left,
                right,
                100,
                parabolaIteration.getFunc(),
                null
        ));
        res.add(Graph.intervalCount(
                parabolaIteration.getLeft(),
                parabolaIteration.getRight(),
                100,
                parabolaIteration.getApproximationParabola(),
                null
        ));
//        addSinglePointGraph(res, extremum);
        parabolaIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Parabola";
    }

    @Override
    public OptimizationMethodIteration getIteration() {
        return parabolaIteration;
    }
}
