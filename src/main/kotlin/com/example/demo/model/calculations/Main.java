package com.example.demo.model.calculations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.ParabolaIteration;
import com.example.demo.model.optimizations.Dichotomy;
import com.example.demo.model.optimizations.GoldenRatio;
import com.example.demo.model.optimizations.OptimizationMethodRunner;
import com.example.demo.model.optimizations.Parabola;

public class Main {
    public static void main(String[] args) {
        DoubleFunction func = value -> -3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
//        Parabola parabola = new Parabola(0, 2 * Math.PI, 1e-8);
//        System.out.println(parabola.run(false));
        Dichotomy dichotomy = new Dichotomy(0, 2 * Math.PI, 1e-8, 1e-9);
//        System.out.println(dichotomy.run(true));
        System.out.println(OptimizationMethodRunner.run(new ParabolaIteration(0, 2 * Math.PI, 1e-3, func), true));
//        GoldenRatio goldenRatio = new GoldenRatio(0, 2 * Math.PI, 1e-8);
//        System.out.println(goldenRatio.run(false));
//        ParabolaIteration iter = new ParabolaIteration(0, 2 * Math.PI, 1e-8, func);
    }
}
