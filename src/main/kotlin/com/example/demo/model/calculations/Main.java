package com.example.demo.model.calculations;

import com.example.demo.model.base.DoubleFunction;
import com.example.demo.model.iterations.*;
import com.example.demo.model.optimizations.*;

import java.util.Arrays;

import static com.example.demo.model.base.FibonacciCalculator.fib;

public class Main {
    public static void main(String[] args) {
        DoubleFunction f = value -> -3 * value * Math.sin(value * 0.75) + Math.exp(-2 * value);
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
//        System.out.println(OptimizationMethodRunner.run(new DichotomyIteration(0, 2 * Math.PI, 1e-3, 1e-5, f), true).getExtremum());
//        System.out.println(OptimizationMethodRunner.run(new GoldenRatioIteration(0, 2 * Math.PI, 1e-3, f), true).getExtremum());
        System.out.println(OptimizationMethodRunner.run(new ParabolaIteration(0, 2 * Math.PI, 1e-3, f), true).getExtremum());
        {
            // -log(eps) -> calcs
//            double[] eps = new double[]{1e-1, 1e-2, 1e-3, 1e-4, 1e-5, 1e-6, 1e-7, 1e-8, 1e-9};
            double[] eps = new double[]{1.0000000e-01, 9.8989899e-02, 9.7979798e-02, 9.6969697e-02,
                    9.5959596e-02, 9.4949495e-02, 9.3939394e-02, 9.2929293e-02,
                    9.1919192e-02, 9.0909091e-02, 8.9898990e-02, 8.8888889e-02,
                    8.7878788e-02, 8.6868687e-02, 8.5858586e-02, 8.4848485e-02,
                    8.3838384e-02, 8.2828283e-02, 8.1818182e-02, 8.0808081e-02,
                    7.9797980e-02, 7.8787879e-02, 7.7777778e-02, 7.6767677e-02,
                    7.5757576e-02, 7.4747475e-02, 7.3737374e-02, 7.2727273e-02,
                    7.1717172e-02, 7.0707071e-02, 6.9696970e-02, 6.8686869e-02,
                    6.7676768e-02, 6.6666667e-02, 6.5656566e-02, 6.4646465e-02,
                    6.3636364e-02, 6.2626263e-02, 6.1616162e-02, 6.0606061e-02,
                    5.9595960e-02, 5.8585859e-02, 5.7575758e-02, 5.6565657e-02,
                    5.5555556e-02, 5.4545455e-02, 5.3535354e-02, 5.2525253e-02,
                    5.1515152e-02, 5.0505051e-02, 4.9494950e-02, 4.8484849e-02,
                    4.7474748e-02, 4.6464647e-02, 4.5454546e-02, 4.4444445e-02,
                    4.3434344e-02, 4.2424243e-02, 4.1414142e-02, 4.0404041e-02,
                    3.9393940e-02, 3.8383839e-02, 3.7373738e-02, 3.6363637e-02,
                    3.5353536e-02, 3.4343435e-02, 3.3333334e-02, 3.2323233e-02,
                    3.1313132e-02, 3.0303031e-02, 2.9292930e-02, 2.8282829e-02,
                    2.7272728e-02, 2.6262627e-02, 2.5252526e-02, 2.4242425e-02,
                    2.3232324e-02, 2.2222223e-02, 2.1212122e-02, 2.0202021e-02,
                    1.9191920e-02, 1.8181819e-02, 1.7171718e-02, 1.6161617e-02,
                    1.5151516e-02, 1.4141415e-02, 1.3131314e-02, 1.2121213e-02,
                    1.1111112e-02, 1.0101011e-02, 9.0909100e-03, 8.0808090e-03,
                    7.0707080e-03, 6.0606070e-03, 5.0505060e-03, 4.0404050e-03,
                    3.0303040e-03, 2.0202030e-03, 1.0101020e-03, 1.0000000e-09};
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
}
