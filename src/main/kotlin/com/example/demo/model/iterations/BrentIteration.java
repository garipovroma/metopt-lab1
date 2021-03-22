package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

public class BrentIteration extends AbstractMethodIteration {
    private static final double K = (3. - Math.sqrt(5.)) / 2.;
    private double x;
    private double w;
    private double v;
    private double fx;
    private double fw;
    private double fv;
    private double d;
    private double u;
    private double e;
    private boolean accepted = false;
    private ParabolaIteration.Parabola parabola;

    public BrentIteration(double left, double right, double eps, DoubleFunction function) {
        super(left, right, eps, function);
        x = w = v = left + K * (right - left);
        fx = fw = fv = function.apply(x);
        d = e = right - left;
    }

    @Override
    protected Point getExtremumImpl() {
        return new Point(x, apply(x));
    }

    @Override
    public boolean hasNext() {
        double tol = tol(x);
        return !(Math.abs(x - (left + right) / 2.0) + (right - left) / 2.0 < 2 * tol + eps);
    }

    private static boolean different(double a, double b, double c, double eps) {
        return Math.abs(a - b) > eps && Math.abs(a - c) > eps && Math.abs(c - b) > eps;
    }

    private double tol(double x) {
        return eps * Math.abs(x) + eps / 10.0;
    }

    public boolean isParabolaAccepted() {
        return accepted;
    }

    public double getU() {
        return u;
    }

    public ParabolaIteration.Parabola getParabola() {
        return parabola;
    }

    @Override
    public void next() {
        double tol = tol(x);
        double newE = d;
        accepted = false;
        u = 0.0;
        if (different(x, w, v, eps) && different(fx, fw, fv, eps)) {
            parabola = ParabolaIteration.findApproximationParabola(x, w, v, fx, fw, fv);
            u = ParabolaIteration.findParabolaMinX(x, w, v, fx, fw, fv);
            if (u >= left && u <= right && Math.abs(u - x) < e / 2.0) {
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
            v = w;
            w = x;
            x = u;
            fv = fw;
            fw = fx;
            fx = fu;
        } else {
            if (u >= x) {
                newRight = u;
            } else {
                newLeft = u;
            }
            if (fu <= fw || Math.abs(w - x) < eps) {
                v = w;
                w = u;
                fv = fw;
                fw = fu;
            } else if (fu <= fv || Math.abs(v - x) < eps || Math.abs(v - w) < eps) {
                v = u;
                fv = fu;
            }
        }
        left = newLeft;
        right = newRight;
        d = newD;
        e = newE;
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
                ", tol=" + tol(x) +
                '}';
    }

    @Override
    public String toTex() {
        return String.format("%.4f & %.4f & %.4f & %.4f & %.4f & %.4f & %.4f & %.4f \\\\\n\\hline", left, right, x, w, v, fx, fw, fv);
    }
}
