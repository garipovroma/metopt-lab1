package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class ParabolaIteration extends AbstractMethodIteration {
    private final static int INITIAL_POINT_SEARCH_STEPS = 20;
    private double x1;
    private double x2;
    private double x3;
    private double fx1;
    private double fx2;
    private double fx3;
    private double pMinX;
    private double fOfMinX;
    private DoubleFunction approximationParabola;
    private boolean isFirst;
    private double prevPMinX;

    public static Point findParabolaMin(double x1, double x2, double x3, double fx1, double fx2, double fx3,
                                        DoubleFunction func) {
        double x = findParabolaMinX(x1, x2, x3, fx1, fx2, fx3);
        double y = func.apply(x);
        return new Point(x, y);
    }

    public static double findParabolaMinX(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        double a1 = (fx2 - fx1) / (x2 - x1),
                a2 = ( (fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1) ) / (x3 - x2);
        return (x1 + x2 - a1 / a2) / 2;
    }

    private Point findParabolaMin() {
        return findParabolaMin(x1, x2, x3, fx1, fx2, fx3, function);
    }

    private static int compare(double x, double y) {
        return Double.compare(x, y);
    }

    private int compareWithEps(double x, double y) {
        if (Math.abs(x - y) < eps) {
            return 0;
        }
        if (x - y <= -eps) {
            return -1;
        } else {    /*if (x - y >= eps) {*/
            return 1;
        }
    }
    private double findInitialPoint(double l, double r, double fx1, double fx3) {
        double x2;
        for (int i = 0; i < INITIAL_POINT_SEARCH_STEPS; i++) {
            x2 =  l + ((r - l) / INITIAL_POINT_SEARCH_STEPS) * (i + 1);
            double fx2 = apply(x2);
            if (compareWithEps(fx2, fx1) <= 0 && compareWithEps(fx2, fx3) <= 0) {
                return x2;
            }
        }
        throw new RuntimeException("Can't find initial x2 value");
    }

    public DoubleFunction getApproximationParabola() {
        return approximationParabola;
    }

    public double getpMinX() {
        return pMinX;
    }

    public double getFofMinX() {
        return fOfMinX;
    }

    private static class Parabola {
        private final double a, b, c;

        private Parabola(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public DoubleFunction toDoubleFunction() {
            return x -> a * x * x + b * x + c;
        }
    }

    public ParabolaIteration(double left, double right, double eps, DoubleFunction func) {
        super(left, right, eps, func);
        this.isFirst = true;
        this.x1 = left;
        this.x3 = right;
        this.fx1 = apply(x1);
        this.fx3 = apply(x3);
        this.x2 = findInitialPoint(left, right, fx1, fx3);
        this.fx2 = apply(x2);
        Parabola parabola = findApproximationParabola();
        Point pMin = findParabolaMin();
        this.pMinX = pMin.getX();
        this.fOfMinX = pMin.getY();
        this.approximationParabola = parabola.toDoubleFunction();
        this.prevPMinX = Double.NaN;
    }

    public static Parabola findApproximationParabola(double x1, double x2, double x3, double fx1, double fx2, double fx3) {
        double a0 = fx1, a1 = (fx2 - fx1) / (x2 - x1),
                a2 = ( (fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1) ) / (x3 - x2);
        return new Parabola(a2, a1 + a2 * (-x2) + a2 * (-x1), a0 + a1 * (-x1) + a2 * (-x1) * (-x2));
    }

    private Parabola findApproximationParabola() {
        return findApproximationParabola(x1, x2, x3, fx1, fx2, fx3);
    }

    @Override
    public boolean hasNext() { return isFirst || compareWithEps(prevPMinX, pMinX) != 0; }

    @Override
    public void next() {
        double nx1 = x1, nx2 = x2, nx3 = x3;
        double nfx1 = fx1, nfx2 = fx2, nfx3 = fx3;
        if (compare(x1, pMinX) <= 0 && compare(pMinX, x2) < 0) { // x1 <= pMinX < x2
            if (compare(fOfMinX, fx2) >= 0) { // pMin >= f(x2)
                nx1 = pMinX;
                nfx1 = fOfMinX;
            } else { // pMin < f(x2)
                nx3 = x2;
                nfx3 = fx2;
                nx2 = pMinX;
                nfx2 = fOfMinX;
            }
        } else if (compare(x2, pMinX) <= 0 && compare(pMinX, x3) <= 0) { // x2 <= pMinX <= x3
            if (compare(fx2, fOfMinX) >= 0) { // f(x2) >= pMin
                nx1 = x2;
                nfx1 = fx2;
                nx2 = pMinX;
                nfx2 = fOfMinX;
            } else { // f(x2) < pMin
                nx3 = pMinX;
                nfx3 = fOfMinX;
            }
        }
        left = nx1;
        right = nx3;
        x1 = nx1;
        x2 = nx2;
        x3 = nx3;
        fx3 = nfx3;
        fx2 = nfx2;
        fx1 = nfx1;
        isFirst = false;
        prevPMinX = pMinX;
        Parabola parabola = findApproximationParabola();
        Point pMin = findParabolaMin();
        pMinX = pMin.getX();
        fOfMinX = pMin.getY();
        approximationParabola = parabola.toDoubleFunction();
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
        return "ParabolaIteration{" +
                "left=" + left +
                ", right=" + right +
                ", eps=" + eps +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", fx1=" + fx1 +
                ", fx2=" + fx2 +
                ", fx3=" + fx3 +
                ", pMinX=" + pMinX +
                ", fOfMinX=" + fOfMinX +
                ", approximationParabola=" + approximationParabola +
                ", isFirst=" + isFirst +
                ", func=" + function +
                '}';
    }

    @Override
    public String toTex() {
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", x1, x2, x3, fx1, fx2, fx3, pMinX, fOfMinX);
    }

    @Override
    protected Point getExtremumImpl() {
        return new Point(getpMinX(), getFofMinX());
    }
}
