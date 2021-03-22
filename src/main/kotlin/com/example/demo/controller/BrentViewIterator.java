package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.BrentIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.iterations.ParabolaIteration;
import com.example.demo.model.optimizations.BrentMethod;
import com.example.demo.model.optimizations.OptimizationMethodResult;

import java.util.ArrayList;
import java.util.List;

public class BrentViewIterator extends BaseViewIterator {
    private final Point extremum;
    private final BrentIteration brentIteration;
    private final DoubleFunction function;

    public BrentViewIterator(double left, double right, double eps, DoubleFunction function) {
        super(left, right);
        OptimizationMethodResult result = new BrentMethod(left, right, eps, function).run(false);
        extremum = result.getExtremum();
        iterationsCount = result.getIterationCount();
        brentIteration = new BrentIteration(left, right, eps, function);
        this.function = function;
    }

    @Override
    public OptimizationMethodIteration getIteration() {
        return brentIteration;
    }

    @Override
    public boolean hasNext() {
        return currentIteration < iterationsCount;
    }

    @Override
    public List<Graph> next() {
        currentIteration++;
        List<Graph> res = new ArrayList<>();
        res.add(Graph.intervalCount(left, right, 100, function, "function"));
        Point leftPoint = new Point(getIteration().getLeft(), apply(getIteration().getLeft()));
        Point rightPoint = new Point(getIteration().getRight(), apply(getIteration().getRight()));
        addSinglePointGraph(res, leftPoint, "left: " + leftPoint);
        addSinglePointGraph(res, rightPoint, "right: " + rightPoint);
        addSinglePointGraph(res, extremum, "extremum: " + extremum);
        if (brentIteration.isParabolaAccepted()) {
            ParabolaIteration.Parabola parabola = brentIteration.getParabola();
            double pMinX = brentIteration.getU();
            Point pMin = new Point(pMinX, apply(pMinX));
            addSinglePointGraph(res, pMin, "parabola minimum: " + pMin);
            res.add(Graph.intervalCount(left, right, 100, parabola.toDoubleFunction(), "parabola"));
        }
        brentIteration.next();
        return res;
    }

    @Override public String toString() {
        return "Brent";
    }
}
