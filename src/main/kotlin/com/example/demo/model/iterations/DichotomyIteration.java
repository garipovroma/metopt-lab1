package com.example.demo.model.iterations;

import java.util.function.DoubleFunction;

public class DichotomyIteration {
    private final double left;
    private final double right;
    private final double delta;
    private final double eps;
    private final double x1;
    private final double x2;
    private final static double compareAccuracy = 1e-8;
    private final DoubleFunction<Double> func;

    public DichotomyIteration(double left, double right, double eps, double delta, DoubleFunction<Double> func) {
        this.left = left;
        this.right = right;
        this.delta = delta;
        this.eps = eps;
        this.func = func;
        this.x1 = (right + left - delta) / 2.0;
        this.x2 = (right + left + delta) / 2.0;
    }

    private double f(double x) {
        return func.apply(x);
    }

    public boolean hasNext() {
        return ((right - left) > eps * 2.0);
    }

    public DichotomyIteration next() {
        double x1 = (right + left - delta) / 2.0;
        double x2 = (right + left + delta) / 2.0;
        double fx1 = f(x1);
        double fx2 = f(x2);
        return fx1 <= fx2 ?
                new DichotomyIteration(left, x2, delta, eps, func) :
                new DichotomyIteration(x1, right, delta, eps, func);
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getDelta() {
        return delta;
    }

    public double getEps() {
        return eps;
    }

    public static double getCompareAccuracy() {
        return compareAccuracy;
    }

    public DoubleFunction<Double> getFunc() {
        return func;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }


    @Override
    public String toString() {
        return "DichotomyIteration{" +
                "left=" + left +
                ", right=" + right +
                ", delta=" + delta +
                ", eps=" + eps +
                ", func=" + func +
                '}';
    }
}
