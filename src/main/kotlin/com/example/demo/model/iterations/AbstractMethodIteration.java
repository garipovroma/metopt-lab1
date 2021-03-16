package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public abstract class AbstractMethodIteration implements OptimizationMethodIteration {
    protected final DoubleFunction function;

    protected AbstractMethodIteration(DoubleFunction function) {
        this.function = function;
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
