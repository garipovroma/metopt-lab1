package com.example.demo.model.optimizations;

import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.OptimizationMethodIteration;

public class OptimizationMethodRunner {
    public static Point run(OptimizationMethodIteration iteration, boolean print) {
        if (print) {
            System.out.println(iteration);
        }
        while (iteration.hasNext()) {
            iteration = iteration.next();
            if (print) {
                System.out.println(iteration);
            }
        }
        return iteration.getExtremum();
    }
}