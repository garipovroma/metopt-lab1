package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.ParabolaIteration;

public class ParabolaMethod extends AbstractOptimizationMethod {

    public ParabolaMethod(double left, double right, double eps, DoubleFunction function) {
        super(new ParabolaIteration(left, right, eps, function));
    }
}
