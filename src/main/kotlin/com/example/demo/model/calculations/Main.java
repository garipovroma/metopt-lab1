package com.example.demo.model.calculations;

import com.example.demo.model.optimizations.Dichotomy;

import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {
        DoubleFunction func = new DoubleFunction<Double>() {
            @Override
            public Double apply(double value) {
                return 3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
            }
        };
        Dichotomy dichotomy = new Dichotomy();
        dichotomy.run(0, Math.PI * 2.0, 1e-9, 1e-10, true);
    }
}

