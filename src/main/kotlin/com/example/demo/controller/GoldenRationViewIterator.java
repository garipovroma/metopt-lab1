package com.example.demo.controller;

import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.iterations.GoldenRatioIteration;
import com.example.demo.model.optimizations.Dichotomy;

import java.util.ArrayList;
import java.util.List;

public class GoldenRationViewIterator extends BaseViewIterator {
    private GoldenRatioIteration goldenRatioIteration;
    private final Point extremum;

    public GoldenRationViewIterator(double left, double right, double eps, double delta) {
        this.extremum = new Dichotomy(left, right, eps, delta, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x)).run(false);
        this.goldenRatioIteration = new GoldenRatioIteration(left, right, eps, x -> -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x));
    }

    public boolean hasNext() {
        return goldenRatioIteration.hasNext();
    }

    public List<Graph> next() {
        List<Graph> res = new ArrayList<>();
        res.add(
                new Graph(
                        goldenRatioIteration.getLeft(),
                        goldenRatioIteration.getRight(),
                        100,
                        goldenRatioIteration.getFunc()
                ));
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX1(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX1())
                )
        );
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX2(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX2())
                )
        );
        addSinglePointGraph(res,
                new Point(
                        goldenRatioIteration.getX1(),
                        goldenRatioIteration.getFunc().apply(goldenRatioIteration.getX1())
                )
        );
        addSinglePointGraph(res, extremum);
        goldenRatioIteration = goldenRatioIteration.next();
        return res;
    }

    @Override
    public String toString() {
        return "Golden Ratio";
    }
}
