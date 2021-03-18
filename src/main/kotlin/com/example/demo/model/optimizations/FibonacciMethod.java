package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.FibonacciIteration;

public class FibonacciMethod extends AbstractOptimizationMethod {

    public FibonacciMethod(double left, double right, double eps, DoubleFunction function) {
        super(new FibonacciIteration(left, right, eps, function));
    }
}
