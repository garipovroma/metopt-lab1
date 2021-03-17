package com.example.demo.controller;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.FibonacciIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;
import com.example.demo.model.optimizations.Fibonacci;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.model.base.FibonacciCalculator.calculateIterationsCount;
import static com.example.demo.model.base.FibonacciCalculator.fib;

public class FibonacciViewIterator extends BaseViewIterator {
    private FibonacciIteration fibonacciIteration;
    private final Point extremum;

    public FibonacciViewIterator(double left, double right, double eps, DoubleFunction function) {
        this.extremum = new Fibonacci(left, right, eps).run(false);
        int n = calculateIterationsCount(left, right, eps);
        double x1 = left + fib(n) / fib(n + 2) * (right - left);
        double x2 = left + fib(n + 1) / fib(n + 2) * (right - left);
        this.fibonacciIteration = new FibonacciIteration(left, right, eps,
                x1, x2, function.apply(x1), function.apply(x2), n, 0, function, 0, left, right);
    }

    public boolean hasNext() {
        return fibonacciIteration.hasNext();
    }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
                Graph.intervalCount(
                        fibonacciIteration.getLeft(),
                        fibonacciIteration.getRight(),
                        100,
                        fibonacciIteration.getFunc(),
                        null
                ));
        addSinglePointGraph(res,
                new Point(
                        fibonacciIteration.getX1(),
                        fibonacciIteration.getFunc().apply(fibonacciIteration.getX1())
                ), null
        );
        addSinglePointGraph(res,
                new Point(
                        fibonacciIteration.getX2(),
                        fibonacciIteration.getFunc().apply(fibonacciIteration.getX2())
                ), null
        );
        addSinglePointGraph(res, extremum, null);
        fibonacciIteration = fibonacciIteration.next();
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
