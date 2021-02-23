package com.example.demo.model.calculations;

import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {
        DoubleFunction func = new DoubleFunction<double>() {
            @Override
            public double apply(double value) {
                return 3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
            }
        };

    }
}

