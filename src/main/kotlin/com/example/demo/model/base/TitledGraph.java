package com.example.demo.model.base;

import java.util.List;

public class TitledGraph extends BaseGraph {
    private final String message;

    public TitledGraph(List<Point> points, String message) {
        super(points);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
