package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.optimizations.DichotomyMethod;
import com.example.demo.model.optimizations.OptimizationMethodResult;
import com.example.demo.model.optimizations.OptimizationMethodRunner;

import java.util.ArrayList;
import java.util.List;

public class DichotomyViewIterator extends BaseViewIterator {
    private final DichotomyIteration iteration;
    private final OptimizationMethodResult result;
    private int currentIteration = 0;

    public DichotomyViewIterator(double left, double right, double eps, double delta, DoubleFunction func) {
        super(left, right);
        this.iteration = new DichotomyIteration(left, right, eps, delta, func);
        this.result = new DichotomyMethod(left, right, eps, delta, func).run(false);
        iterationsCount = result.getIterationCount();
    }

    public boolean hasNext() {
        return currentIteration < iterationsCount;
    }

    public List<Graph> next() {
        currentIteration++;
        List<Graph> res = new ArrayList<>();
        res.add(
            Graph.intervalCount(
                left,
                right,
                100,
                iteration.getFunction(),
                "function"
            ));
        Point leftPoint =
            new Point(
                iteration.getLeft(),
                apply(iteration.getLeft())
            );
        addSinglePointGraph(res,
            leftPoint,
            "left: (" + leftPoint.getX() + ", " + leftPoint.getY() + ")"
        );
        Point rightPoint =
            new Point(
                iteration.getRight(),
                apply(iteration.getRight())
            );
        addSinglePointGraph(res,
            new Point(
                iteration.getRight(),
                apply(iteration.getRight())
            ),
            "right: (" + rightPoint.getX() + ", " + rightPoint.getY() + ")"
        );
        Point extremum = result.getExtremum();
        addSinglePointGraph(res, extremum, "extremum: (" + extremum.getX() + ", " + extremum.getY() + ")");
        iteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Dichotomy";
    }

    @Override
    public OptimizationMethodIteration getIteration() {
        return iteration;
    }
}
