package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;

public class DoubleFunctionCounter implements DoubleFunction {
    public int count = 0;
    @Override
    public double apply(double x) {
        count++;
        return -3 * x * Math.sin(x * 0.75) + Math.exp(-2 * x);
    }
}
