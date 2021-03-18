package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.BrentIteration;

public class BrentMethod extends AbstractOptimizationMethod {

    public BrentMethod(double left, double right, double eps, DoubleFunction function) {
        super(new BrentIteration(left, right, eps, function));
    }
}
