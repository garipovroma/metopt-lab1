package com.example.demo.model.optimizations;

import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.OptimizationMethodIteration;

public class OptimizationMethodRunner {
    public static OptimizationMethodResult run(OptimizationMethodIteration iteration, boolean print) {
        if (print) {
            System.out.println(iteration);
        }
        int counter = 1;
        while (iteration.hasNext()) {
            iteration = iteration.next();
            if (print) {
                System.out.println(iteration);
            }
            counter++;
        }
        return new OptimizationMethodResult(iteration.getExtremum(), counter);
    }
}