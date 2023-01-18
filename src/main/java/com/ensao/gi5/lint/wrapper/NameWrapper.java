package com.ensao.gi5.lint.wrapper;

public class NameWrapper {

    private final String name;
    private final int line;
    private final String type;

    public NameWrapper(String name, int line, String type) {

        this.name = name;
        this.line = line;
        this.type = type;
    }

    public String getName() { return this.name; }
    public int getLine() { return this.line; }
    public String getType() { return this.type; }
}