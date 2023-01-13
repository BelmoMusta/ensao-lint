package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Constructor;

import java.lang.reflect.Method;
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
