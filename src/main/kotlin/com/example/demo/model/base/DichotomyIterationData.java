package com.example.demo.model.base;

public class DichotomyIterationData {
    private final double left, right, leftValue, rightValue, length, iterationNum;
    private final IterationResult result;

    public DichotomyIterationData(double left, double right, double leftValue, double rightValue, double length, double iterationNum, IterationResult result) {
        this.left = left;
        this.right = right;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.length = length;
        this.iterationNum = iterationNum;
        this.result = result;
    }

    public enum IterationResult {
        LEFT, RIGHT, NONE;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getLeftValue() {
        return leftValue;
    }

    public double getRightValue() {
        return rightValue;
    }

    public double getLength() {
        return length;
    }

    public double getIterationNum() {
        return iterationNum;
    }

    public IterationResult getResult() {
        return result;
    }
}
