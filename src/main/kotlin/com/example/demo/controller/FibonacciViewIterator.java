package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.FibonacciIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.optimizations.OptimizationMethodRunner;

import java.util.ArrayList;
import java.util.List;

public class FibonacciViewIterator extends BaseViewIterator {
    private FibonacciIteration fibonacciIteration;
    private final Point extremum;
    private final double left;
    private final double right;

    public FibonacciViewIterator(double left, double right, double eps, DoubleFunction function) {
        this.left = left;
        this.right = right;
        this.fibonacciIteration = new FibonacciIteration(left, right, eps, function);
        this.extremum = OptimizationMethodRunner.run(fibonacciIteration, false).getExtremum();
    }

    public boolean hasNext() {
        return fibonacciIteration.hasNext();
    }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
                Graph.intervalCount(
                        left,
                        right,
                        100,
                        fibonacciIteration.getFunc(),
                        null
                ));
        addSinglePointGraph(res,
                new Point(
                        fibonacciIteration.getX1(),
                        fibonacciIteration.getFunc().apply(fibonacciIteration.getX1())
                ), "x1"
        );
        addSinglePointGraph(res,
                new Point(
                        fibonacciIteration.getX2(),
                        fibonacciIteration.getFunc().apply(fibonacciIteration.getX2())
                ), "x2"
        );
        addSinglePointGraph(res, extremum, "extremum");
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
