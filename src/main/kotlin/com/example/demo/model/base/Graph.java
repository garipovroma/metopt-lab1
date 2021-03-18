package com.example.demo.model.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Graph {
    private final List<Point> points;
    private final Optional<String> message;

    public Graph(List<Point> points, String message) {
        this.points = points;
        this.message = Optional.ofNullable(message);
    }

    public static Graph intervalCount(double left, double right, int count, DoubleFunction func, String message) {
        return intervalStep(left, right, (right - left) / ((double) (count)), func, message);
    }

    public static Graph intervalStep(double left, double right, double step, DoubleFunction func, String message) {
        List<Point> points = new ArrayList<>();
        while (left < right) {
            points.add(new Point(left, func.apply(left)));
            left += step;
        }
        return new Graph(points, message);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return message.orElse("Graph{" + "points=" + points + '}');
    }
}
