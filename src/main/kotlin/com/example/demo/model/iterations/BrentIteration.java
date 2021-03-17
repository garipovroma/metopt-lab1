package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BrentIteration extends AbstractMethodIteration {
    private static final double K = (3. - Math.sqrt(5.)) / 2.;
    private final double left;
    private final double right;
    private final double eps;
    private final double x;
    private final double w;
    private final double v;
    private final double d;
//    private final double g;
    private final double e;
    private final double tol;

    private BrentIteration(DoubleFunction function, double left, double right, double eps, double x, double w, double v, double d, double e) {
        super(function);
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.x = x;
        this.w = w;
        this.v = v;
        this.d = d;
        this.e = e;
        this.tol = eps * Math.abs(x) + eps / 10.0;
    }

    public BrentIteration(double left, double right, double eps, DoubleFunction function) {
        super(function);
        this.left = left;
        this.right = right;
        this.eps = eps;
        x = w = v = left + K * (right - left);
        d = e = right - left;
        this.tol = eps * Math.abs(x) + eps / 10.0;
    }

    @Override
    protected Point getExtremumImpl() {
        return new Point(x, apply(x));
    }

    @Override
    public boolean hasNext() {
        return !(Math.abs(x - (left + right) / 2.0) + (right - left) / 2.0 < 2 * tol + eps);
    }

    private static boolean different(double a, double b, double c, double eps) {
        return Math.abs(a - b) > eps && Math.abs(a - c) > eps && Math.abs(c - b) > eps;
    }

    @Override
    public OptimizationMethodIteration next() {
        double g = e;
        double newE = d;
        double fx = apply(x);
        double fv = apply(v);
        double fw = apply(w);
        boolean accepted = false;
        double u = 0xDEAD_BEEF;
        if (different(x, w, v, eps) && different(fx, fw, fv, eps)) {
            List<Double> o4ko = Arrays.stream(new Double[]{x, w, v}).sorted().collect(Collectors.toList());
            Point point = ParabolaIteration.findParabolaMin(o4ko.get(0), o4ko.get(1), o4ko.get(2), fx, fw, fv, function, true);
            u = point.getX();
//            u = new ParabolaIteration(left, right, eps, function).getpMinX();
            if (u >= left && u <= right && Math.abs(u - x) < g / 2.0) {
                accepted = true;
            } else if (u - left < 2.0 * tol || right - u < 2.0 * tol) {
                u = x - Math.signum(x - (left + right) / 2.0) * tol;
                accepted = true;
            }
        }
        if (!accepted) {
            if (x < (left + right) / 2.0) {
                u = x + K * (right - x);
                newE = right - x;
            } else {
                u = x - K * (x - left);
                newE = x - left;
            }
        }
        if (Math.abs(u - x) < tol) {
            u = x + Math.signum(u - x) * tol;
        }
        double newD = Math.abs(u - x);
        double fu = apply(u);
        double newLeft = left;
        double newRight = right;
        if (fu <= fx) {
            if (u >= x) {
                newLeft = x;
            } else {
                newRight = x;
            }
            return new BrentIteration(function, newLeft, newRight, eps, u, x, w, newD, newE);
        } else {
            if (u >= x) {
                newRight = u;
            } else {
                newLeft = u;
            }
            if (fu <= fw || Math.abs(w - x) < eps) {
                return new BrentIteration(function, newLeft, newRight, eps, x, u, w, newD, newE);
            } else if (fu <= fv || Math.abs(v - x) < eps || Math.abs(v - w) < eps) {
                return new BrentIteration(function, newLeft, newRight, eps, x, w, u, newD, newE);
            }
            return new BrentIteration(function, newLeft, newRight, eps, x, w, v, newD, newE);
        }
    }

    @Override
    public String toString() {
        return "BrentIteration{" +
                "left=" + left +
                ", right=" + right +
                ", eps=" + eps +
                ", x=" + x +
                ", w=" + w +
                ", v=" + v +
                ", d=" + d +
                ", e=" + e +
                ", tol=" + tol +
                '}';
    }
}
