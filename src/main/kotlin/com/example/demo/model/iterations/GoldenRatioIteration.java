package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class GoldenRatioIteration extends AbstractMethodIteration {
    public final static double TAU = (Math.sqrt(5.0) - 1.0) / 2.0;
    private final double x1;
    private final double x2;
    private final double fx1;
    private final double fx2;

    public GoldenRatioIteration(double left, double right, double eps, DoubleFunction func) {
        this(left, right, left + (1.0 - TAU) * (right - left), left + TAU * (right - left), 0, 0, eps, func, 0);
    }

    private GoldenRatioIteration(double left, double right, double x1, double x2, double fx1, double fx2, double eps, DoubleFunction func, int calcLeft) {
        super(left, right, eps, func);
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

    @Override
    public boolean hasNext() {
        return ((right - left) > eps * 2.0);
    }

    @Override
    public GoldenRatioIteration next() {
        return fx1 <= fx2 ?
            new GoldenRatioIteration(left, x2, x2 - TAU * (x2 - left), x1, fx1, fx2, eps, function, -1) :
            new GoldenRatioIteration(x1, right, x2, x1 + TAU * (right - x1), fx1, fx2, eps, function, 1);

    }

    public Point getExtremumImpl() {
        double x = (left + right) / 2.0;
        return new Point(x, apply(x));
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
        return function;
    }

    @Override
    public String toString() {
        return "GoldenRatioIteration{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", fx1=" + fx1 +
                ", fx2=" + fx2 +
                '}';
    }

    @Override
    public String toTex() {
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", left, right, x1, x2, fx1, fx2);
    }
}
