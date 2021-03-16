package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.GoldenRatioIteration;

@Deprecated
public class GoldenRatio {
    private GoldenRatioIteration iteration;
    public GoldenRatio(double left, double right, double eps) {
        this.iteration = new GoldenRatioIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }
    public GoldenRatio(double left, double right, double eps, DoubleFunction func) {
        this.iteration = new GoldenRatioIteration(left, right, eps, func);
    }

    public Point run(boolean print) {
        if (print) {
            if (print) {
                System.out.println("$l$ & $r$ & $x_1$ & $x_2$ & $fx_1$ & $fx_2$ \\\\");
                System.out.println(iteration);
            }
            System.out.println(iteration);
        }
        while (iteration.hasNext()) {
            iteration = iteration.next();
            if (print) {
                System.out.println(iteration);
            }
        }
        double x = (iteration.getLeft() + iteration.getRight()) / 2.0;
        double y = (iteration.getFunc().apply(x));
        if (print) {
            System.out.format("extremumX=%.4f extremumY=%.4f\n", x, y);
        }
        return new Point(x, y);
    }
}
