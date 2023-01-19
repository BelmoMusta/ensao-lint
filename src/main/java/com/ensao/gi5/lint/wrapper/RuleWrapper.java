package com.ensao.gi5.lint.wrapper;

public class RuleWrapper {
    private String name;
    private int line;
    public RuleWrapper(String name,int line) {
        this.name = name;
        this.line = line;
    }
    public String getName() {
        return name;
    }

    public int getLine(){
        return line;
    }
}
