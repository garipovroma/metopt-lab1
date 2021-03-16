package com.example.demo.model.iterations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;

import java.util.Arrays;
import java.util.HashSet;
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
    private final double g;
    private final double e;
    private final double tol;

    private BrentIteration(DoubleFunction function, double left, double right, double eps, double x, double w, double v, double d, double g, double e) {
        super(function);
        this.left = left;
        this.right = right;
        this.eps = eps;
        this.x = x;
        this.w = w;
        this.v = v;
        this.d = d;
        this.g = g;
        this.e = e;
        this.tol = eps * Math.abs(x) + eps / 10.0;
    }

    public BrentIteration(double left, double right, double eps, DoubleFunction function) {
        super(function);
        this.left = left;
        this.right = right;
        this.eps = eps;
        x = w = v = left + K * (right - left);
        d = g = e = right - left;
        this.tol = eps * Math.abs(x) + eps / 10.0;
    }

    @Override
    protected Point getExtremumImpl() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return Math.abs(x - (left + right) / 2.0) + (right - left) / 2.0 <= 2 * tol;
    }

    private static boolean different(double a, double b, double c) {
        return Arrays.stream(new Double[]{a, b, c}).collect(Collectors.toSet()).size() == 3;
    }

    @Override
    public OptimizationMethodIteration next() {
        double fx = apply(x);
        double fv = apply(v);
        double fw = apply(w);
        if (different(x, w, v) && different(fx, fv, fw)) {
            double u = new ParabolaIteration(left);
        }
        return null;
    }
}
