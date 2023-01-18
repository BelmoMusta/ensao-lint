package com.ensao.gi5.lint.wrapper;


import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodsWrapper {
    private final String name;
    private final int line;

    public MethodsWrapper(MethodDeclaration methodDeclaration) {
        this.name = methodDeclaration.getNameAsString();
        this.line = methodDeclaration.getBegin().map(p->p.line).orElse(-1);
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }
}

