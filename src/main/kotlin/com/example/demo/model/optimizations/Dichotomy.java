package com.example.demo.model.optimizations;

import com.example.demo.model.base.Point;

import java.util.function.DoubleFunction;

public class Dichotomy {
    private final double left;
    private final double right;
    private final double delta;
    private final double eps;
    private final static double compareAccuracy = 1e-8;
    private final DoubleFunction<Double> func;

    public Dichotomy(double left, double right, double eps, double delta, DoubleFunction<Double> func) {
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.delta = delta;
        this.func = func;
    }

    public Dichotomy(double left, double right, double eps, double delta) {
        this(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }

    private double f(double x) {
        return -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }

    public Point run() {
        return run(left, right, eps, delta, false);
    }

    private Point run(double a, double b, double eps, double delta, boolean print) {
        int iter = 0;
        while (b - a > eps) {
            double x1 = (b + a - delta) / 2.0;
            double x2 = (b + a + delta) / 2.0;
            double fx1 = f(x1);
            double fx2 = f(x2);
            if (print) {
                System.out.println(iter + " " + a + " " + b + " " + x1 + " " + x2 + " " + fx1 + " " + fx2);
            }
            iter++;
            if (fx1 <= fx2) {
                b = x2;
            } else {
                a = x1;
            }
        }
        double x = (a + b) / 2.0;
        double fx = f(x);
        return new Point(x, fx);
    }

    @Override
    public String toString() {
        return "Dichotomy";
    }


}
