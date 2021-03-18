package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.OptimizationMethodIteration;

public class DichotomyMethod extends AbstractOptimizationMethod {

    public DichotomyMethod(double left, double right, double eps, double delta, DoubleFunction function) {
        super(new DichotomyIteration(left, right, eps, delta, function));
    }
}
