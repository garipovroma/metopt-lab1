package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.optimizations.Dichotomy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public class DichotomyViewIterator {
    private DichotomyIteration dichotomyIteration;
    private final Point extremum;

    public DichotomyViewIterator(double left, double right, double eps, double delta) {
        this.extremum = new Dichotomy(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x)).run(false);
        this.dichotomyIteration = new DichotomyIteration(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }

    public boolean hasNext() {
        return dichotomyIteration.hasNext();
    }

     private void addSinglePointGraph(List<Graph> points, Point point) {
        List<Point> single = new ArrayList<>();
        single.add(point);
        points.add(new Graph(single));

     }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
            new Graph(
                dichotomyIteration.getLeft(),
                dichotomyIteration.getRight(),
                100,
                dichotomyIteration.getFunc()
            ));
        addSinglePointGraph(res,
            new Point(
                dichotomyIteration.getX1(),
                dichotomyIteration.getFunc().apply(dichotomyIteration.getX1())
            )
        );
        addSinglePointGraph(res,
            new Point(
                dichotomyIteration.getX2(),
                dichotomyIteration.getFunc().apply(dichotomyIteration.getX2())
            )
        );
        addSinglePointGraph(res,
            new Point(
                dichotomyIteration.getX1(),
                dichotomyIteration.getFunc().apply(dichotomyIteration.getX1())
            )
        );
        addSinglePointGraph(res, extremum);
        dichotomyIteration = dichotomyIteration.next();
        return res;
    }
}
