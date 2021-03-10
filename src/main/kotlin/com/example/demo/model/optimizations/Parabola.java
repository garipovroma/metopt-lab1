package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.ParabolaIteration;

public class Parabola {
    private ParabolaIteration iteration;

    public Parabola(double left, double right, double eps, DoubleFunction func) {
        iteration = new ParabolaIteration(left, right, eps, func);
    }

    public Parabola(double left, double right, double eps) {
        iteration = new ParabolaIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }

    public Point run(boolean print) {
        if (print) {
            System.out.println(iteration);
        }
        while (iteration.hasNext()) {
            iteration = iteration.next();
            if (print) {
                System.out.println(iteration);
            }
        }
        double x = iteration.getpMinX();
        double y = iteration.getpMinY();
        return new Point(x, y);
    }


    /*private final double EPS;
    private final double left, right;

    public Parabola(double left, double right, double EPS) {
        this.left = left;
        this.right = right;
        this.EPS = EPS;
    }

    private double findInitialPoint(double l, double r) {
        double x1 = l, x3 = r, x2;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            x2 = random.nextDouble() * (r - l) + l;
            if (compare(f(x2), f(x1)) <= 0 && compare(f(x2), f(x3)) <= 0) {
                return x2;
            }
        }
        throw new RuntimeException("Can't find initial x2 value");
    }
    public double f(double x) {
        return -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }
    private int compare(double x, double y) {
        return Double.compare(x, y);
    }
    private int compareWithEps(double x, double y) {
        if (Math.abs(x - y) < EPS) {
            return 0;
        }
        if (x - y <= -EPS) {
            return -1;
        } else if (x - y >= EPS) {
            return 1;
        }
        return 1;
    }
    private double findParabolaMinX(double x1, double x2, double x3) {
        double f1 = f(x1), f2 = f(x2), f3 = f(x3);
        double a0 = f1, a1 = (f2 - f1) / (x2 - x1),
                a2 = ( (x3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1) ) / (x3 - x2);
        return (x1 + x2 - a1 / a2) / 2;
    }
    public Point run() {
        double x1 = left, x2 = findInitialPoint(left, right), x3 = right;
        int iter = 0;
        boolean firstIteration = true;
        double minX = Double.NaN, minY;
        double prevIterationMinX = Double.NaN;
        while (true) {
            double f1 = f(x1), f2 = f(x2), f3 = f(x3);
            double pMinX = findParabolaMinX(x1, x2, x3);
            double pMinY = f(pMinX);

            System.out.println(iter + " " + left + " " + right + " " +
                    x1 + " " + x2 + " " + x3 + " " +
                    f1 + " " + f2 + " " + f3 + " " +
                    pMinX + " " + pMinY);

            if (!firstIteration) {
                if (compareWithEps(pMinX, prevIterationMinX) == 0) {
                    minX = pMinX;
                    break;
                }
            } else {
                firstIteration = false;
            }

            // bounds calc
            {
                // pMin = ParabolaMinY, pMinx = ParabolaMinX
                if (compare(x1, pMinX) <= 0 && compare(pMinX, x2) < 0) { // x1 <= pMinX < x2
                    if (compare(pMinY, f2) >= 0) { // pMin >= f(x2)
                        x1 = pMinX;
                    } else { // pMin < f(x2)
                        x3 = x2;
                        x2 = pMinX;
                    }
                } else if (compare(x2, pMinX) <= 0 && compare(pMinX, x3) <= 0) { // x2 <= pMinX <= x3
                    if (compare(f2, pMinY) >= 0) { // f(x2) >= pMin
                        x1 = x2;
                        x2 = pMinX;
                    } else { // f(x2) < pMin
                        x3 = pMinX;
                    }
                }
            }
            // Now x1, x2, x3 are new bounds
            prevIterationMinX = pMinX;
            iter++;
            *//*if (iter == 11) {
                break;
            }*//*
        }
        minX = prevIterationMinX;
        minY = f(minX);
        return new Point(minX, minY);
    }*/

}
