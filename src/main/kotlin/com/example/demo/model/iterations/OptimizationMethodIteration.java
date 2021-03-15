package com.example.demo.model.iterations;

import com.example.demo.model.base.Point;

public interface OptimizationMethodIteration {
    boolean hasNext();
    OptimizationMethodIteration next();
    Point getExtremum();
}
