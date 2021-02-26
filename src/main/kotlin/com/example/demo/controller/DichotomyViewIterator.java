package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.iterations.DichotomyIteration;

import java.util.ArrayList;
import java.util.List;

public class DichotomyViewIterator {
    private DichotomyIteration dichotomyIteration;

    public DichotomyViewIterator(DichotomyIteration dichotomyIteration) {
        this.dichotomyIteration = dichotomyIteration;
    }

    public boolean hasNext() {
        return dichotomyIteration.hasNext();
    }

    // gprivate addPoint(List<Graph> points, Point point) {}

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
            new Graph(
                dichotomyIteration.getLeft(),
                dichotomyIteration.getRight(),
                100,
                dichotomyIteration.getFunc()
            ));
        res.add(
            new Graph(
                new ArrayList<>()
            ));
        dichotomyIteration = dichotomyIteration.next();
        return res;
    }
}
