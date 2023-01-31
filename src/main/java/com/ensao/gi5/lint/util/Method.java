package com.ensao.gi5.lint.util;

public class Method {
    final private String name;
    private int linesCount;

    public Method(String name){
        this.linesCount = 0;
        this.name = name;

    }

    public void setLinesCount(int linesCounts) {
        this.linesCount = linesCounts;
    }

    public int getLinesCount() {
        return linesCount;
    }

}
