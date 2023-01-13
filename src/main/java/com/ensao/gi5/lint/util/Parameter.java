package com.ensao.gi5.lint.util;

public class Parameter {
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    final private String type;
    final private String name;

    public Parameter(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
