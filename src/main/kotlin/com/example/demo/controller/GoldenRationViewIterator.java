package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.GoldenRatioIteration;
import com.example.demo.model.optimizations.Dichotomy;
import com.example.demo.model.optimizations.OptimizationMethodRunner;
import com.example.demo.model.optimizations.GoldenRatio;

import java.util.ArrayList;
import java.util.List;

public class GoldenRationViewIterator extends BaseViewIterator {
    private GoldenRatioIteration goldenRatioIteration;
    private final Point extremum;

    public GoldenRationViewIterator(double left, double right, double eps) {
        this.goldenRatioIteration = new GoldenRatioIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
        this.extremum = OptimizationMethodRunner.run(goldenRatioIteration, false);
    }

    public boolean hasNext() {
        return goldenRatioIteration.hasNext();
    }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
                Graph.intervalCount(
                        goldenRatioIteration.getLeft(),
                        goldenRatioIteration.getRight(),
                        100,
                        goldenRatioIteration.getFunc(),
                        null
                ));
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX1(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX1())
                ), null
        );
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX2(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX2())
                ), null
        );
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX1(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX1())
                ), null
        );
        addSinglePointGraph(res, extremum, null);
        goldenRatioIteration = goldenRatioIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Golden Ratio";
    }
}
