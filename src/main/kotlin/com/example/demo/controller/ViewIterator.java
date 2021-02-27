package com.example.demo.controller;

import com.example.demo.model.base.Graph;

import java.util.List;

public interface ViewIterator {
    public boolean hasNext();
    public List<Graph> next();
}
