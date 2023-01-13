package com.ensao.gi5.lint.wrapper;

import java.util.HashSet;
import java.util.Set;

public class Constructor {

    final private Set<Parameter> parameters;
    final private int line;
    final private String name;

    public Constructor(String name, int line) {
        this.name = name;
        this.parameters = new HashSet<>();
        this.line = line;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

}
