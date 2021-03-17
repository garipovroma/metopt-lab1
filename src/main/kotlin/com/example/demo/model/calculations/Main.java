package com.example.demo.model.calculations;

import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.*;
import com.example.demo.model.optimizations.*;

import java.util.Arrays;

import static com.example.demo.model.base.FibonacciCalculator.fib;

public class Main {
    public static void main(String[] args) {
//        DoubleFunction func = value -> -3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
//        Parabola parabola = new Parabola(0, 2 * Math.PI, 1e-8);
//        System.out.println(parabola.run(false));
//        System.out.println(dichotomy.run(true));
//        GoldenRatio goldenRatio = new GoldenRatio(0, 2 * Math.PI, 1e-8);
//        System.out.println(goldenRatio.run(false));
//        ParabolaIteration iter = new ParabolaIteration(0, 2 * Math.PI, 1e-8, func);
//        System.out.println(new Parabola(0, 2 * Math.PI, 1e-4, func).run(true));

//        Point result = OptimizationMethodRunner.run(new DichotomyIteration(0, 2 * Math.PI, 1e-3, 1e-5, func), true);
//        Point result = OptimizationMethodRunner.run(new GoldenRatioIteration(0, 2 * Math.PI, 1e-3, func), true);
//        Point result = OptimizationMethodRunner.run(new ParabolaIteration(0, 2 * Math.PI, 1e-3, func), true);
//        System.out.format("%.4f %.4f", result.getX(), result.getY());

        // Statistics
        double[] eps = new double[]{1e-1, 1e-2, 1e-3, 1e-4, 1e-5, 1e-6, 1e-7, 1e-8, 1e-9};

        System.out.println("eps: " + Arrays.toString(eps));

        System.out.print("Dichotomy ");
        for (double x : eps) {
            DoubleFunctionCounter func = new DoubleFunctionCounter();
            OptimizationMethodRunner.run(new DichotomyIteration(0, 2 * Math.PI, x, x / 4 + x * x / 10, func), false);
            System.out.print(func.count + " ");
        }
        System.out.println();

        System.out.print("GoldenRation ");
        for (double x : eps) {
            DoubleFunctionCounter func = new DoubleFunctionCounter();
            OptimizationMethodRunner.run(new GoldenRatioIteration(0, 2 * Math.PI, x, func), false);
            System.out.print(func.count + " ");
        }
        System.out.println();

        System.out.print("Fibonacci ");
        for (double x : eps) {
            DoubleFunctionCounter func = new DoubleFunctionCounter();
            OptimizationMethodRunner.run(Fibonacci.Iteration(0, 2 * Math.PI, x, func), false);
            System.out.print(func.count + " ");
        }
        System.out.println();
        System.out.print("Parabola ");
        for (double x : eps) {
            DoubleFunctionCounter func = new DoubleFunctionCounter();
            OptimizationMethodRunner.run(new ParabolaIteration(0, 2 * Math.PI, x, func), false);
            System.out.print(func.count + " ");
        }
        System.out.println();

        // Brent
    }
}
