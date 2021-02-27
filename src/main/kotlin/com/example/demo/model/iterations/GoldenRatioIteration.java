package com.example.demo.model.iterations;

import com.example.demo.model.optimizations.GoldenRatio;

import java.util.function.DoubleFunction;

public class GoldenRatioIteration implements OptimizationMethodIteration {
    private final double left;
    private final double right;
    private final double eps;
    private final double x1;
    private final double x2;
    private final double fx1;
    private final double fx2;
    private final static double compareAccuracy = 1e-8;
    private final DoubleFunction<Double> func;

    public GoldenRatioIteration(double left, double right, double eps, DoubleFunction<Double> func) {
        this(left, right, left + (1.0 - GoldenRatio.tau) * (right - left), left + GoldenRatio.tau * (right - left), 0, 0, eps, func, 0);
    }

    private GoldenRatioIteration(double left, double right, double x1, double x2, double fx1, double fx2, double eps, DoubleFunction<Double> func, int calcLeft) {
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.func = func;
        this.x1 = x1;
        this.x2 = x2;
        if (calcLeft == -1) {
            this.fx1 = func.apply(x1);
            this.fx2 = fx1;
        } else if (calcLeft == 1) {
            this.fx1 = fx2;
            this.fx2 = func.apply(x2);
        } else {
            this.fx1 = func.apply(x1);
            this.fx2 = func.apply(x2);
        }
    }

    private double f(double x) {
        return func.apply(x);
    }

    @Override
    public boolean hasNext() {
        return ((right - left) > eps * 2.0);
    }

    @Override
    public GoldenRatioIteration next() {
        return fx1 <= fx2 ?
                new GoldenRatioIteration(left, x2, x2 - GoldenRatio.tau * (x2 - left), x1, fx1, fx2, eps, func, -1) :
                new GoldenRatioIteration(x1, right, x2, x1 + GoldenRatio.tau * (right - x1), fx1, fx2, eps, func, 1);

    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getEps() {
        return eps;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getFx1() {
        return fx1;
    }

    public double getFx2() {
        return fx2;
    }

    public static double getCompareAccuracy() {
        return compareAccuracy;
    }

    public DoubleFunction<Double> getFunc() {
        return func;
    }

    @Override
    public String toString() {
        return "GoldenRatioIteration{" +
                "left=" + left +
                ", right=" + right +
                ", eps=" + eps +
                ", func=" + func +
                '}';
    }
}
