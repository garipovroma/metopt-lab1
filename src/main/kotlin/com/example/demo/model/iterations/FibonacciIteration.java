package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.FibonacciCalculator;
import com.example.demo.model.base.Point;

import static com.example.demo.model.base.FibonacciCalculator.calculateIterationsCount;
import static com.example.demo.model.base.FibonacciCalculator.fib;

public class FibonacciIteration extends AbstractMethodIteration {
    private final double left;
    private final double right;
    private final double eps;
    private final double x1;
    private final double x2;
    private final double fx1;
    private final double fx2;
    private final int n;
    private final int k;
    private final double len;

    public FibonacciIteration(double left, double right, double eps) {
        this(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }
    public FibonacciIteration(double left, double right, double eps, DoubleFunction func) {
        super(func);
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.n = FibonacciCalculator.calculateIterationsCount(left, right, eps);
        this.x1 = left + fib(n) / fib(n + 2) * (right - left);
        this.x2 = left + fib(n + 1) / fib(n + 2) * (right - left);
        this.fx1 = f(x1);
        this.fx2 = f(x2);
        this.k = 1;
        this.len = right - left;
    }
    private FibonacciIteration(double left, double right, double eps, double x1, double x2,
                              double fx1, double fx2, int n, int k, DoubleFunction func, double len) {
        super(func);
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.x1 = x1;
        this.x2 = x2;
        this.fx1 = fx1;
        this.fx2 = fx2;
        this.n = n;
        this.k = k;
        this.len = len;
    }

    private double f(double x) {
        return function.apply(x);
    }

    @Override
    public boolean hasNext() {
        return (k < n);
    }

    @Override
    public FibonacciIteration next() {
        double newLeft, newRight, newX1, newX2, newFx1, newFx2;
        if (fx1 > fx2) {
            newLeft = x1;
            newRight = right;
            newX1 = x2;
            newX2 = newLeft + fib(n - k + 2) / fib(n + 2) * (len);
            newFx1 = fx2;
            newFx2 = f(newX2);
        } else {
            newLeft = left;
            newRight = x2;
            newX2 = x1;
            newX1 = left + fib(n - k + 1) / fib(n + 2) * (len);
            newFx1 = f(newX1);
            newFx2 = fx1;
        }
        return new FibonacciIteration(newLeft, newRight, eps, newX1, newX2, newFx1, newFx2, n, k + 1, function, len);
    }

    public Point getExtremumImpl() {
        double x = (left + right) / 2.0;
        return new Point(x, apply(x));
    }

    @Override
    public Point getExtremum() {
        if (hasNext())
            throw new UnsupportedOperationException("getExtremum allowed only after all iterations");
        double x = (left + right) / 2.0;
        return new Point(x, apply(x));
    }

    public DoubleFunction getFunc() {
        return this.function;
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
                '}';
    }
}
