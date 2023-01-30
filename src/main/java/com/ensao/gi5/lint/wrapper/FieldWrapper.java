package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Set;
import java.util.stream.Collectors;

public class FieldWrapper {
    private String name;
    private Set<Modifier> modifiers;
    private int line;

    public FieldWrapper(SimpleName name, NodeList<Modifier> modifiers) {
        this.name = name.getIdentifier();
        this.modifiers = modifiers.stream().collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public Set<Modifier> getModifiers() {
        return modifiers;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}



