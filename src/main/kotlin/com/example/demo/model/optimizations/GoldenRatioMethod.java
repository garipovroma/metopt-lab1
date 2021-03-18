package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.GoldenRatioIteration;

public class GoldenRatioMethod extends AbstractOptimizationMethod {

    public GoldenRatioMethod(double left, double right, double eps, DoubleFunction function) {
        super(new GoldenRatioIteration(left, right, eps, function));
    }
}
