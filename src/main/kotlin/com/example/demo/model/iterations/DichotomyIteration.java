package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class DichotomyIteration extends AbstractMethodIteration {
    private final double delta;
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;

    public DichotomyIteration(double left, double right, double eps, double delta, DoubleFunction func) {
        super(left, right, eps, func);
        this.delta = delta;
        this.x1 = (right + left - delta) / 2.0;
        this.x2 = (right + left + delta) / 2.0;
        this.y1 = apply(x1);
        this.y2 = apply(x2);
    }

    @Override
    public boolean hasNext() {
        return ((right - left) > eps * 2.0);
    }

    @Override
    public DichotomyIteration next() {
        return y1 <= y2 ?
            new DichotomyIteration(left, x2, eps, delta, function) :
            new DichotomyIteration(x1, right, eps, delta, function);
    }

    public Point getExtremumImpl() {
        double x = (left + right) / 2.0;
        return new Point(x, apply(x));
    }

    public double getDelta() {
        return delta;
    }

    public DoubleFunction getFunc() {
        return function;
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
                ", x1=" + x1 +
                ", x2=" + x2 +
                '}';
    }

    @Override
    public String toTex() {
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", left, right, x1, x2, y1, y2);
    }
}
