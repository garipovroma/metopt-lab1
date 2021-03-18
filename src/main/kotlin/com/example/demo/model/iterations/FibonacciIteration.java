package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.FibonacciCalculator;
import com.example.demo.model.base.Point;

import static com.example.demo.model.base.FibonacciCalculator.fib;

public class FibonacciIteration extends AbstractMethodIteration {
    private double x1;
    private double x2;
    private double fx1;
    private double fx2;
    private int k;
    private final int n;
    private final double len;

    public FibonacciIteration(double left, double right, double eps, DoubleFunction func) {
        super(left, right, eps, func);
        this.n = FibonacciCalculator.calculateIterationsCount(left, right, eps);
        this.x1 = left + fib(n) / fib(n + 2) * (right - left);
        this.x2 = left + fib(n + 1) / fib(n + 2) * (right - left);
        this.fx1 = apply(x1);
        this.fx2 = apply(x2);
        this.k = 1;
        this.len = right - left;
    }

    @Override
    public boolean hasNext() {
        return (k < n);
    }

    @Override
    public void next() {
        double newLeft, newRight, newX1, newX2, newFx1, newFx2;
        if (fx1 > fx2) {
            newLeft = x1;
            newRight = right;
            newX1 = x2;
            newX2 = newLeft + fib(n - k + 2) / fib(n + 2) * (len);
            newFx1 = fx2;
            newFx2 = apply(newX2);
        } else {
            newLeft = left;
            newRight = x2;
            newX2 = x1;
            newX1 = left + fib(n - k + 1) / fib(n + 2) * (len);
            newFx1 = apply(newX1);
            newFx2 = fx1;
        }
        left = newLeft;
        right = newRight;
        x1 = newX1;
        x2 = newX2;
        fx1 = newFx1;
        fx2 = newFx2;
        k++;
    }

    public Point getExtremumImpl() {
        double x = (left + right) / 2.0;
        return new Point(x, apply(x));
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
        return this.function;
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

    @Override
    public String toTex() {
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", left, right, x1, x2, fx1, fx2);
    }
}
