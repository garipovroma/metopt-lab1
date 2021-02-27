package com.example.demo.controller;

import com.example.demo.model.base.Graph;

import java.util.List;

public interface ViewIterator {
    boolean hasNext();
    List<Graph> next();
}
