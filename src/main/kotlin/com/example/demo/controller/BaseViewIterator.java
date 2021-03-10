package com.example.demo.controller;

import com.example.demo.model.base.BaseGraph;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewIterator implements ViewIterator{
    protected void addSinglePointGraph(List<Graph> points, Point point, String message) {
        List<Point> single = new ArrayList<>();
        single.add(point);
        Graph graph = new BaseGraph(single);
        points.add(message == null? graph : graph.withMessage(message));
    }
}
