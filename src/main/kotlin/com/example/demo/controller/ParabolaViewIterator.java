package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.ParabolaIteration;

import java.util.ArrayList;
import java.util.List;

public class ParabolaViewIterator extends BaseViewIterator{

    private ParabolaIteration parabolaIteration;
    private final Point extremum;
    public ParabolaViewIterator(double left, double right, double eps) {
        this.extremum = new Point(1.0, 1.0);
        this.parabolaIteration = new ParabolaIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }
    @Override
    public boolean hasNext() {
        return parabolaIteration.hasNext();
    }

    @Override
    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        double left = parabolaIteration.getLeft();
        double right = parabolaIteration.getRight();
        res.add(new Graph(
                left,
                right,
                100,
                parabolaIteration.getFunc()
        ));
        res.add(new Graph(
                left,
                right,
                100,
                parabolaIteration.getApproximationParabola()
        ));
        addSinglePointGraph(res, extremum);
        parabolaIteration = parabolaIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Parabola";
    }
}
