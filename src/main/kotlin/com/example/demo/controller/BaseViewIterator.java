package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewIterator implements ViewIterator{
    protected void addSinglePointGraph(List<Graph> points, Point point) {
        List<Point> single = new ArrayList<>();
        single.add(point);
        points.add(new Graph(single));
    }
}
