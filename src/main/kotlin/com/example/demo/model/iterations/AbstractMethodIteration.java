package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public abstract class AbstractMethodIteration implements OptimizationMethodIteration {
    protected final DoubleFunction function;

    public DoubleFunction getFunction() {
        return function;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getEps() {
        return eps;
    }

    protected double left;
    protected double right;
    protected final double eps;

    protected AbstractMethodIteration(double left, double right, double eps, DoubleFunction function) {
        this.function = function;
        this.left = left;
        this.right = right;
        this.eps = eps;
    }

    protected abstract Point getExtremumImpl();

    protected double apply(double x) {
        return function.apply(x);
    }

    public Point getExtremum() {
        if (hasNext()) {
            throw new UnsupportedOperationException("Can't calculate extremum");
        }
        return getExtremumImpl();
    }
}
