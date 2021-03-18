package com.example.demo.model.optimizations;

import com.example.demo.model.iterations.OptimizationMethodIteration;

public abstract class AbstractOptimizationMethod {
    private final OptimizationMethodIteration iteration;

    AbstractOptimizationMethod(OptimizationMethodIteration iteration) {
        this.iteration = iteration;
    }

    public OptimizationMethodResult run(boolean print) {
        if (print) {
            print(0);
        }
        int counter = 1;
        double lastLen = iteration.getRight() - iteration.getLeft();
        while (iteration.hasNext()) {
            iteration.next();
            if (print) {
                print(lastLen);
            }
            lastLen = iteration.getRight() - iteration.getLeft();
            counter++;
        }
        return new OptimizationMethodResult(iteration.getExtremum(), counter);
    }

    public void print(double lastLen) {
        final int offsetInToTexString = 9;
        final int substringConstant = 6;
        double curRatio = (iteration.getRight() - iteration.getLeft()) / lastLen;
        StringBuilder curRes = new StringBuilder(iteration.toTex());
        String endian = curRes.substring(curRes.length() - offsetInToTexString, curRes.length());
        curRes.delete(curRes.length() - offsetInToTexString, curRes.length());
        String curRatioString = "& " + Double.toString(curRatio).substring(0, Math.min(substringConstant,
                Double.toString(curRatio).length()));
        curRes.append(curRatioString.toCharArray(), 0, curRatioString.length());
        curRes.append(endian);
        System.out.println(curRes);
    }
}
