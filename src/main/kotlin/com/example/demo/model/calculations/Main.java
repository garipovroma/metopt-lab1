package com.example.demo.model.calculations;

import com.example.demo.model.optimizations.Dichotomy;
import com.example.demo.model.optimizations.GoldenRatio;
import com.example.demo.model.optimizations.Parabola;

import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {
        DoubleFunction func = new DoubleFunction<Double>() {
            @Override
            public Double apply(double value) {
                return 3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
            }
        };
        System.out.println(new Parabola().run(0, 2 * Math.PI));
    }
}

