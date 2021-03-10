package com.example.demo.model.base;

import java.util.ArrayList;
import java.util.List;

public class BaseGraph implements Graph {
    private final List<Point> points;

    public BaseGraph(double left, double right, int count, DoubleFunction func) {
        this(left, right, (right - left) / ((double) (count)), func);
    }

    public BaseGraph(double left, double right, double step, DoubleFunction func) {
        List<Point> points = new ArrayList<Point>();
        while (left < right) {
            points.add(new Point(left, func.apply(left)));
            left += step;
        }
        this.points = points;
    }

    public BaseGraph(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public Graph withMessage(String message) {
        return new TitledGraph(points, message);
    }

    @Override
    public String toString() {
        return "BaseGraph{" +
                "points=" + points +
                '}';
    }
}
