package com.example.demo.model.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public class Graph {
    private final List<Point> points;

    public Graph(double left, double right, int count, DoubleFunction<Double> func) {
        this(left, right, (right - left) / ((double) (count)), func);
    }

    public Graph(double left, double right, double step, DoubleFunction<Double> func) {
        List<Point> points = new ArrayList<Point>();
        while (left < right) {
            points.add(new Point(left, func.apply(left)));
            left += step;
        }
        this.points = points;
    }

    public Graph(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }
}
