package com.example.demo.model.base;

import java.util.List;

public interface Graph {
    Graph withMessage(String message);
    List<Point> getPoints();
}
