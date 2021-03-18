package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public interface OptimizationMethodIteration {
    boolean hasNext();
    void next();
    Point getExtremum();
    DoubleFunction getFunction();
    double getLeft();
    double getRight();
    double getEps();
}
