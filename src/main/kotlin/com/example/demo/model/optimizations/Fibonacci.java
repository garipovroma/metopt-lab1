package com.example.demo.model.optimizations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.FibonacciIteration;

import static com.example.demo.model.base.FibonacciCalculator.fib;
import static com.example.demo.model.base.FibonacciCalculator.calculateIterationsCount;

public class Fibonacci {
    private FibonacciIteration iteration;
    public static FibonacciIteration Iteration(double left, double right, double eps, DoubleFunction func) {
        int n = calculateIterationsCount(left, right, eps);
        double x1 = left + fib(n) / fib(n + 2) * (right - left);
        double x2 = left + fib(n + 1) / fib(n + 2) * (right - left);
        return new FibonacciIteration(left, right, eps,
                x1, x2, func.apply(x1), func.apply(x2), n, 0,func, 0, left, right);
    }
    public Fibonacci(double left, double right, double eps, DoubleFunction func) {
        iteration = Iteration(left, right, eps, func);
    }
    public Fibonacci(double left, double right, double eps) {
        this(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
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
