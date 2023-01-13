package com.ensao.gi5.lint.wrapper;

public class Element {
    final private String name ;
    final private int line ;

    public Element(String name, int line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }


    public int getLine() {
        return line;
    }

}
