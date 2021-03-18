package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.FibonacciIteration;

import static com.example.demo.model.base.FibonacciCalculator.fib;
import static com.example.demo.model.base.FibonacciCalculator.calculateIterationsCount;

public class Fibonacci {
    private FibonacciIteration iteration;
    public static FibonacciIteration Iteration(double left, double right, double eps) {
        return new FibonacciIteration(left, right, eps);
    }
    public Fibonacci(double left, double right, double eps) {
        iteration = Iteration(left, right, eps);
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
        double x = (iteration.getLeft() + iteration.getRight()) / 2.0;
        double y = (iteration.getFunc().apply(x));
        return new Point(x, y);
    }
}
