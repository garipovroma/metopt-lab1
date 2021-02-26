package com.example.demo.model.calculations;

import com.example.demo.model.optimizations.Dichotomy;
import com.example.demo.model.optimizations.GoldenRatio;

import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {
        DoubleFunction func = new DoubleFunction<Double>() {
            @Override
            public Double apply(double value) {
                return 3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
            }
        };
        Dichotomy dichotomy = new Dichotomy(0, 2 * Math.PI, 1e-8, 1e-9);
        System.out.println(dichotomy.run(true));
        GoldenRatio goldenRatio = new GoldenRatio();
        System.out.println(goldenRatio.run(0, Math.PI * 2.0, 1e-9, true));
    }
}

