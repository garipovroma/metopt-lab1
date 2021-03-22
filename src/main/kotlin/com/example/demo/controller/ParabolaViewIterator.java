package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.iterations.ParabolaIteration;
import com.example.demo.model.optimizations.OptimizationMethodResult;
import com.example.demo.model.optimizations.ParabolaMethod;

import java.util.ArrayList;
import java.util.List;

public class ParabolaViewIterator extends BaseViewIterator{
    private final ParabolaIteration parabolaIteration;
    private final Point extremum;
    private final DoubleFunction function;

    public ParabolaViewIterator(double left, double right, double eps, DoubleFunction function) {
        super(left, right);
        OptimizationMethodResult result = new ParabolaMethod(left, right, eps, function).run(false);
        this.function = function;
        this.parabolaIteration =
            new ParabolaIteration(left, right, eps, function);
        this.extremum = result.getExtremum();
        this.iterationsCount = result.getIterationCount();
    }
    @Override
    public boolean hasNext() {
        return currentIteration < iterationsCount;
    }

    @Override
    public List<Graph> next() {
        currentIteration++;
        List<Graph> res = new ArrayList<>();
        res.add(Graph.intervalCount(
                left,
                right,
                100,
                function,
                "function"
        ));
        res.add(Graph.intervalCount(
                parabolaIteration.getLeft(),
                parabolaIteration.getRight(),
                100,
                parabolaIteration.getApproximationParabola(),
                "parabola"
        ));
        Point pMin = new Point(parabolaIteration.getpMinX(),
            parabolaIteration.getApproximationParabola().apply(parabolaIteration.getpMinX()));
        addSinglePointGraph(res, pMin, "parabola minimum: " + pMin);
        addSinglePointGraph(res, extremum, "extremum: " + extremum);
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
