package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class GoldenRatioIteration extends AbstractMethodIteration {
    public final static double TAU = (Math.sqrt(5.0) - 1.0) / 2.0;
    private double x1;
    private double x2;
    private double fx1;
    private double fx2;

    public GoldenRatioIteration(double left, double right, double eps, DoubleFunction func) {
        super(left, right, eps, func);
        this.x1 = left + (1.0 - TAU) * (right - left);
        this.x2 = left + TAU * (right - left);
        this.fx1 = apply(x1);
        this.fx2 = apply(x2);
    }

    @Override
    public boolean hasNext() {
        return ((right - left) > eps * 2.0);
    }

    @Override
    public void next() {
        if (fx1 <= fx2) {
            right = x2;
            x1 = x2 - TAU * (x2 - left);
            x2 = x1;
            fx2 = fx1;
            fx1 = apply(x1);
        } else {
            left = x1;
            x2 = x1 + TAU * (right - x1);
            x1 = x2;
            fx1 = fx2;
            fx2 = apply(x2);
        }
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
        /*return "GoldenRatioIteration{" +
                "left=" + left +
                ", right=" + right +
                ", eps=" + eps +
                ", func=" + func +
                '}';*/
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", left, right, x1, x2, fx1, fx2);
    }
}
