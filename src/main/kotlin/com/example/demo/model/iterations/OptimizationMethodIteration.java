package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public interface OptimizationMethodIteration {
    boolean hasNext();
    OptimizationMethodIteration next();
    Point getExtremum();
    DoubleFunction getFunction();
    double getLeft();
    double getRight();
    double getEps();
    public String toTex();
}
