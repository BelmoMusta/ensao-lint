package com.ensao.gi5.lint.wrapper;

public class Wrapper {
    private final String name;
    private final int line;

    public Wrapper(String name, int line) {
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
