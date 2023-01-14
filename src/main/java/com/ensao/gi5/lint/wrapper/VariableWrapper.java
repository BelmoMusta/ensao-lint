package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;

public class VariableWrapper {
    private final String name;
    private final int line;
    private final boolean used;

    public VariableWrapper(VariableDeclarator variableDeclarator) {
        this.name = variableDeclarator.getNameAsString();
        this.line = variableDeclarator.getBegin().map(p->p.line).orElse(-1);
        this.used = variableDeclarator.getParentNode().isPresent() && variableDeclarator.getParentNode().get().findAll(NameExpr.class)
                .stream().anyMatch(n -> n.getNameAsString().equals(name));
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public boolean isUsed() {
        return used;
    }
}

