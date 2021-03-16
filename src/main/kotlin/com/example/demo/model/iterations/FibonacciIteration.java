package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;

import static com.example.demo.model.base.FibonacciCalculator.calculateIterationsCount;
import static com.example.demo.model.base.FibonacciCalculator.fib;

public class FibonacciIteration implements OptimizationMethodIteration {
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
    private final double initial_right;
    private final double initial_left;

    public FibonacciIteration(double left, double right, double eps, double x1, double x2,
                              double fx1, double fx2, int n, int k, DoubleFunction func, int calcLeft,
                              double initial_left, double initial_right) {
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.x1 = x1;
        this.x2 = x2;
        this.n = n;
        this.k = k;
        this.func = func;
        if (calcLeft == -1) {
            this.fx1 = fx2;
            this.fx2 = f(x2);
        } else if (calcLeft == 1) {
            this.fx1 = f(x1);
            this.fx2 = fx1;
        } else {
            this.fx1 = f(x1);
            this.fx2 = f(x2);
        }
        this.initial_left = initial_left;
        this.initial_right = initial_right;
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
                new FibonacciIteration(x1, right, eps,
                        left + fib(n - k + 1) / fib(n + 2) * (initial_right - initial_left),
                        left + fib(n - k + 2) / fib(n + 2) * (initial_right - initial_left),
                        fx1, fx2, n, k + 1, func, 1, initial_left, initial_right) :
                new FibonacciIteration(left, x2, eps,
                        left + fib(n - k + 1) / fib(n + 2) * (initial_right - initial_left),
                        left + fib(n - k + 2) / fib(n + 2) * (initial_right - initial_left),
                        fx1, fx2, n, k + 1, func, -1, initial_left, initial_right);
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
