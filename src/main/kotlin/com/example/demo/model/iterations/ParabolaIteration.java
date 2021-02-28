package com.example.demo.model.iterations;

import org.ejml.data.DMatrixRMaj;
import org.ejml.simple.SimpleMatrix;

import java.util.Random;
import java.util.function.DoubleFunction;

public class ParabolaIteration implements OptimizationMethodIteration {
    private final double left;
    private final double right;
    private final double eps;
    private final double x1;
    private final double x2;
    private final double x3;
    private final double fx1;
    private final double fx2;
    private final double fx3;
    private final double pMinX;
    private final double pMinY;
    private final DoubleFunction<Double> approximationParabola;
    private final boolean isFirst;
    private final double prevPMinX;

    private final DoubleFunction<Double> func;

    private double findParabolaMinX() {
        double a0 = fx1, a1 = (fx2 - fx1) / (x2 - x1),
                a2 = ( (x3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1) ) / (x3 - x2);
        return (x1 + x2 - a1 / a2) / 2;
    }
    private int compare(double x, double y) {
        return Double.compare(x, y);
    }
    private int compareWithEps(double x, double y) {
        if (Math.abs(x - y) < eps) {
            return 0;
        }
        if (x - y <= -eps) {
            return -1;
        } else if (x - y >= eps) {
            return 1;
        }
        return 1;
    }
    private double findInitialPoint(double l, double r) {
        double _x2;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            _x2 = random.nextDouble() * (r - l) + l;
            double _f2 = f(_x2);
            if (compare(_f2, fx1) <= 0 && compare(_f2, fx3) <= 0) {
                return _x2;
            }
        }
        throw new RuntimeException("Can't find initial x2 value");
    }

    public DoubleFunction<Double> getApproximationParabola() {
        return approximationParabola;
    }

    public double getpMinX() {
        return pMinX;
    }

    public double getpMinY() {
        return pMinY;
    }

    public ParabolaIteration(double left, double right, double eps, DoubleFunction<Double> func) {
        this.isFirst = true;
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.func = func;
        this.x1 = left;
        this.x3 = right;
        this.fx1 = f(x1);
        this.fx3 = f(x3);
        this.x2 = findInitialPoint(left, right);
        this.fx2 = f(x2);
        this.pMinX = findParabolaMinX();
        this.pMinY = f(pMinX);
        this.approximationParabola = findApproximationParabola();
        this.prevPMinX = Double.NaN;
    }

    private ParabolaIteration(double left, double right, double x1, double x2, double x3, double fx1, double fx2, double fx3, double eps, DoubleFunction<Double> func, double prevPMinX) {
        this.left = left;
        this.right = right;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.fx1 = fx1;
        this.fx2 = fx2;
        this.fx3 = fx3;
        this.func = func;
        this.isFirst = false;
        this.eps = eps;
        this.pMinX = findParabolaMinX();
        this.pMinY = f(pMinX);
        this.approximationParabola = findApproximationParabola();
        this.prevPMinX = prevPMinX;
    }

    private DoubleFunction<Double> findApproximationParabola() {
        SimpleMatrix equation = new SimpleMatrix(
                new DMatrixRMaj(
                        new double[][]{
                                {x1 * x1, x1, 1},
                                {x2 * x2, x2, 1},
                                {x3 * x3, x3, 1}
                        }));
        SimpleMatrix solution = equation.solve(new SimpleMatrix(new DMatrixRMaj(new double[][]{
                {fx1},
                {fx2},
                {fx3}
        })));
        double coefficientA = solution.get(0, 0);
        double coefficientB = solution.get(1, 0);
        double coefficientC = solution.get(2, 0);
        return (x -> coefficientA * x * x + coefficientB * x + coefficientC);
    }

    private double f(double x) {
        return func.apply(x);
    }

    @Override
    public boolean hasNext() { return isFirst || compareWithEps(prevPMinX, pMinX) == 0; }

    @Override
    public ParabolaIteration next() {
        double nx1 = x1, nx2 = x2, nx3 = x3;
        double nfx1 = fx1, nfx2 = fx2, nfx3 = fx3;
        if (compare(x1, pMinX) <= 0 && compare(pMinX, x2) < 0) { // x1 <= pMinX < x2
            if (compare(pMinY, fx2) >= 0) { // pMin >= f(x2)
                nx1 = pMinX;
                nfx1 = pMinY;
            } else { // pMin < f(x2)
                nx3 = x2;
                nfx3 = fx2;
                nx2 = pMinX;
                nfx2 = pMinY;
            }
        } else if (compare(x2, pMinX) <= 0 && compare(pMinX, x3) <= 0) { // x2 <= pMinX <= x3
            if (compare(fx2, pMinY) >= 0) { // f(x2) >= pMin
                nx1 = x2;
                nfx1 = fx2;
                nx2 = pMinX;
                nfx2 = pMinY;
            } else { // f(x2) < pMin
                nx3 = pMinX;
                nfx3 = pMinY;
            }
        }
        return new ParabolaIteration(nx1, nx3, nx1, nx2, nx3, nfx1, nfx2, nfx3, eps, func, pMinX);
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
                ", pMinY=" + pMinY +
                ", approximationParabola=" + approximationParabola +
                ", isFirst=" + isFirst +
                ", func=" + func +
                '}';
    }
}
