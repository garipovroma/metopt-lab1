package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewIterator implements ViewIterator{
    protected void addSinglePointGraph(List<Graph> graphs, Point point, String message) {
        List<Point> single = new ArrayList<>();
        single.add(point);
        graphs.add(new Graph(single, message));
    }
}
