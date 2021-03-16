package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class FibonacciIteration implements OptimizationMethodIteration {
    private static final double INFINITY = 1e18;
    private final double left;
    private final double right;
    private final double eps;
    private final double x1;
    private final double x2;
    private final double fx1;
    private final double fx2;
    private final int n;
    private final int k;
    private final DoubleFunction func;

    public FibonacciIteration(double left, double right, double eps, double x1, double x2, int n, int k, DoubleFunction func) {
        this(left, right, eps, x1, x2, INFINITY, INFINITY, n, k, func, 0);
    }
    public FibonacciIteration(double left, double right, double eps, double x1, double x2,
                              double fx1, double fx2, int n, int k, DoubleFunction func, int calcLeft) {
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.x1 = x1;
        this.x2 = x2;
        this.n = n;
        this.k = k;
        this.func = func;
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
        return (k < n);
    }

    @Override
    public FibonacciIteration next() {
        return fx1 > fx2 ?
            new FibonacciIteration(x1, right, eps, x2, right - (x1 - left), fx1, fx2, n, k + 1, func, 1) :
            new FibonacciIteration(left, x2, eps,left + (right - x2), x1, fx1, fx2, n, k + 1, func, -1);
    }

    @Override
    public Point getExtremum() {
        return null;
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

    public DoubleFunction getFunc() {
        return func;
    }

    @Override
    public String toString() {
        return "FibonacciIteration{" +
                "left=" + left +
                ", right=" + right +
                ", eps=" + eps +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", fx1=" + fx1 +
                ", fx2=" + fx2 +
                ", n=" + n +
                ", k=" + k +
                ", func=" + func +
                '}';
    }
}
