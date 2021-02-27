package com.example.demo.model.optimizations;

import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.GoldenRatioIteration;

import java.util.function.DoubleFunction;

public class GoldenRatio {
    public final static double tau = (Math.sqrt(5.0) - 1.0) / 2.0;
    private GoldenRatioIteration iteration;
    public GoldenRatio(double left, double right, double eps) {
        this.iteration = new GoldenRatioIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }
    public GoldenRatio(double left, double right, double eps, DoubleFunction<Double> func) {
        this.iteration = new GoldenRatioIteration(left, right, eps, func);
    }

    public Point run(boolean print) {
        if (print) {
            System.out.println(iteration);
        }
        while (iteration.hasNext()) {
            iteration = iteration.next();
            if (print) {
                System.out.println(iteration);
            }
        }
        double x = (iteration.getLeft() + iteration.getRight()) / 2.0;
        double y = (iteration.getFunc().apply(x));
        return new Point(x, y);
    }
}
