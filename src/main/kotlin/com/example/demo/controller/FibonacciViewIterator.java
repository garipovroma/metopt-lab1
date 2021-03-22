package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.FibonacciIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.optimizations.FibonacciMethod;
import com.example.demo.model.optimizations.OptimizationMethodResult;
import com.example.demo.model.optimizations.OptimizationMethodRunner;

import java.util.ArrayList;
import java.util.List;

public class FibonacciViewIterator extends BaseViewIterator {
    private final FibonacciIteration fibonacciIteration;
    private final Point extremum;
    public FibonacciViewIterator(double left, double right, double eps, DoubleFunction function) {
        super(left, right);
        this.fibonacciIteration = new FibonacciIteration(left, right, eps, function);
        OptimizationMethodResult result = new FibonacciMethod(left, right, eps, function).run(false);
        this.extremum = result.getExtremum();
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
                        fibonacciIteration.getFunc(),
                        "function"
                ));
        Point leftPoint = new Point(fibonacciIteration.getLeft(), apply(fibonacciIteration.getLeft()));
        addSinglePointGraph(res, leftPoint, "left: " + leftPoint);
        Point rightPoint = new Point(getIteration().getRight(), apply(getIteration().getRight()));
        addSinglePointGraph(res, rightPoint, "right: " + rightPoint);
        addSinglePointGraph(res, extremum, "extremum: " + extremum);
        fibonacciIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Fibonacci";
    }

    @Override
    public OptimizationMethodIteration getIteration() {
        return fibonacciIteration;
    }
}
