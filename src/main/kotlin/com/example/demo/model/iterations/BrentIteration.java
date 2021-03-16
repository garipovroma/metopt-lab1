package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class BrentIteration extends AbstractMethodIteration {
    private static final double K = (3. - Math.sqrt(5.)) / 2.;
    private final double left;
    private final double right;
    private final double eps;
    private final double x;
    private final double w;
    private final double v;

    public BrentIteration(double left, double right, double eps, DoubleFunction func) {
        super(func);
        this.left = left;
        this.right = right;
        this.eps = eps;
        x = w = v = left + K * (right - left);
    }

    @Override
    protected Point getExtremumImpl() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public OptimizationMethodIteration next() {
        return null;
    }
}
