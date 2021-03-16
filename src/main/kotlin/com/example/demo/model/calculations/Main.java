package com.example.demo.model.calculations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.ParabolaIteration;
import com.example.demo.model.optimizations.*;

public class Main {
    public static void main(String[] args) {
        Dichotomy dichotomy = new Dichotomy(0, 2 * Math.PI, 1e-8, 1e-9);
        System.out.println(dichotomy.run(false));
        DoubleFunction func = value -> -3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
/*        Parabola parabola = new Parabola(0, 2 * Math.PI, 1e-8);
        System.out.println(parabola.run(true));
        System.out.println(dichotomy.run(true));
        System.out.println(OptimizationMethodRunner.run(new ParabolaIteration(0, 2 * Math.PI, 1e-3, func), true));
        GoldenRatio goldenRatio = new GoldenRatio(0, 2 * Math.PI, 1e-8);
        System.out.println(goldenRatio.run(false));
        ParabolaIteration iter = new ParabolaIteration(0, 2 * Math.PI, 1e-8, func);
        System.out.println(new Parabola(0, 2 * Math.PI, 1e-4, func).run(true));
*/
        Fibonacci fibonacci = new Fibonacci(0, 2 * Math.PI, 1e-7);
        System.out.println(fibonacci.run(true));
    }
}
