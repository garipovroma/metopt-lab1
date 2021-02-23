package com.example.demo.model.base;

import java.util.List;

public class DichotomyData {
    private final List<?> iterationsList;
    private final double extremumPoint, extremumValue, accuracy;
    private final ExtremumType extremumType;
    private final long calculationsCount;

    public DichotomyData(List<?> iterationsList, double extremumPoint, double extremumValue, double accuracy, ExtremumType extremumType, long calculationsCount) {
        this.iterationsList = iterationsList;
        this.extremumPoint = extremumPoint;
        this.extremumValue = extremumValue;
        this.accuracy = accuracy;
        this.extremumType = extremumType;
        this.calculationsCount = calculationsCount;
    }

    public List<?> getIterationsList() {
        return iterationsList;
    }

    public double getExtremumPoint() {
        return extremumPoint;
    }

    public double getExtremumValue() {
        return extremumValue;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public ExtremumType getExtremumType() {
        return extremumType;
    }

    public long getCalculationsCount() {
        return calculationsCount;
    }

    public enum ExtremumType {
        MINIMUM, MAXIMUM;
    }
}
