package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.GoldenRatioIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.optimizations.GoldenRatioMethod;
import com.example.demo.model.optimizations.OptimizationMethodResult;
import com.example.demo.model.optimizations.OptimizationMethodRunner;

import java.util.ArrayList;
import java.util.List;

public class GoldenRationViewIterator extends BaseViewIterator {
    private final GoldenRatioIteration goldenRatioIteration;
    private final Point extremum;

    public GoldenRationViewIterator(double left, double right, double eps, DoubleFunction function) {
        super(left, right);
        this.goldenRatioIteration = new GoldenRatioIteration(left, right, eps, function);
        OptimizationMethodResult result = new GoldenRatioMethod(left, right, eps, function).run(false);
        this.extremum = result.getExtremum();
        this.iterationsCount = result.getIterationCount();
    }

    public boolean hasNext() {
        return currentIteration < iterationsCount;
    }

    public List<Graph> next() {
        currentIteration++;
        List<Graph> res = new ArrayList<>();
        Point leftPoint = new Point(
            goldenRatioIteration.getLeft(),
            goldenRatioIteration.getFunc().apply(goldenRatioIteration.getLeft()));
        Point rightPoint = new Point(
            goldenRatioIteration.getRight(),
            goldenRatioIteration.getFunc().apply(goldenRatioIteration.getRight()));
        res.add(
                Graph.intervalCount(
                        left,
                        right,
                        100,
                        goldenRatioIteration.getFunc(),
                        "function"
                ));
        addSinglePointGraph(res, leftPoint, "left: " + leftPoint);
        addSinglePointGraph(res, rightPoint, "right" + rightPoint);
        addSinglePointGraph(res, extremum, "extremum: " + extremum);
        goldenRatioIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Golden Ratio";
    }

    @Override
    public OptimizationMethodIteration getIteration() {
        return goldenRatioIteration;
    }
}
