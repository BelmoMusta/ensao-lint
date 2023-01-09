package com.ensao.gi5.lint.wrapper.ClassWrapper;

import java.util.HashSet;
import java.util.Set;

public class ClassWrapper {
    final private String name;
    final private Set<Constructor> constructors;
    final private Set<Method> methods;
    final private int line;


    public ClassWrapper(String name, int line) {
        this.name = name;
        this.line = line;
        this.constructors = new HashSet<>();
        this.methods = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public Set<Method> getMethods() {
        return methods;
    }

    public Set<Constructor> getConstructors() {
        return constructors;
    }
}




