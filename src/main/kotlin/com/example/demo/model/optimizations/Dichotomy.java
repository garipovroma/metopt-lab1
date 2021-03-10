package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;

public class Dichotomy {
    private DichotomyIteration iteration;

    public Dichotomy(double left, double right, double eps, double delta, DoubleFunction func) {
        this.iteration = new DichotomyIteration(left, right, eps, delta, func);
    }

    public Dichotomy(double left, double right, double eps, double delta) {
        this(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
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

    @Override
    public String toString() {
        return "Dichotomy";
    }
}
