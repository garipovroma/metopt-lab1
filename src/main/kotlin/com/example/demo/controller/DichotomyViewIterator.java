package com.example.demo.controller;

import com.example.demo.model.base.BaseGraph;
import com.example.demo.model.base.Graph;
import com.example.demo.model.base.Point;
import com.example.demo.model.iterations.DichotomyIteration;
import com.example.demo.model.optimizations.Dichotomy;

import java.util.ArrayList;
import java.util.List;

public class DichotomyViewIterator extends BaseViewIterator {
    private DichotomyIteration dichotomyIteration;
    private final double left;
    private final double right;
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
        res.add(
            new BaseGraph(
//                dichotomyIteration.getLeft(),
//                dichotomyIteration.getRight(),
                left,
                right,
                100,
                dichotomyIteration.getFunc()
            ).withMessage("function"));
        addSinglePointGraph(res,
            new Point(
                dichotomyIteration.getX1(),
                dichotomyIteration.getFunc().apply(dichotomyIteration.getX1())
            ),
            "left"
        );
        addSinglePointGraph(res,
            new Point(
                dichotomyIteration.getX2(),
                dichotomyIteration.getFunc().apply(dichotomyIteration.getX2())
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
        return res;
    }

    @Override
    public String toString() {
        return "Dichotomy";
    }
}
