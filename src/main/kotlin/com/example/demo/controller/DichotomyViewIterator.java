package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.optimizations.Dichotomy;

import java.util.ArrayList;
import java.util.List;

public class DichotomyViewIterator extends BaseViewIterator {
    private DichotomyIteration dichotomyIteration;
    private double left;
    private double right;
    private final Point extremum;

    public DichotomyViewIterator(double left, double right, double eps, double delta) {
        this.extremum = new Dichotomy(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x)).run(true);
        this.dichotomyIteration = new DichotomyIteration(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
        this.left = dichotomyIteration.getLeft();
        this.right = dichotomyIteration.getRight();
    }

    public boolean hasNext() {
        return dichotomyIteration.hasNext();
    }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        double offset = Math.max(right - extremum.getX(), extremum.getX() - left);
        res.add(
            Graph.intervalCount(
                left,
                right,
                100,
                dichotomyIteration.getFunc(),
                "function"
            ));
        addSinglePointGraph(res,
            new Point(
                left,
                dichotomyIteration.getFunc().apply(left)
            ),
            "left"
        );
        addSinglePointGraph(res,
            new Point(
                right,
                dichotomyIteration.getFunc().apply(right)
            ),
            "right"
        );
//        addSinglePointGraph(res,
//            new Point(
//                dichotomyIteration.getX1(),
//                dichotomyIteration.getFunc().apply(dichotomyIteration.getX1())
//            )
//        );
        addSinglePointGraph(res, extremum, "extremum");
        dichotomyIteration = dichotomyIteration.next();
        left = dichotomyIteration.getLeft();
        right = dichotomyIteration.getRight();
        return res;
    }

    @Override
    public String toString() {
        return "Dichotomy";
    }
}
