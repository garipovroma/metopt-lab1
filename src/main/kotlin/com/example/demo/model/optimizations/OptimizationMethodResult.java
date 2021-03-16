package com.example.demo.model.optimizations;

import com.example.demo.model.base.Point;

public class OptimizationMethodResult {
    private final Point extremum;
    private final int iterationCount;

    public OptimizationMethodResult(Point extremum, int iterationCount) {
        this.extremum = extremum;
        this.iterationCount = iterationCount;
    }

    public Point getExtremum() {
        return extremum;
    }

    public int getIterationCount() {
        return iterationCount;
    }
}
