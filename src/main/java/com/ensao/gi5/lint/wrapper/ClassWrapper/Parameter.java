package com.ensao.gi5.lint.wrapper.ClassWrapper;

public class Parameter{
    final private String type;
    final private String name;

    public Parameter(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.type + " " + this.name;
    }
}