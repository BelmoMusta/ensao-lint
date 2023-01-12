package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariableNameWrapper {


    private final int line;
    private final String name;


    public LocalVariableNameWrapper(VariableDeclarator variableDeclarator) {
        this.line = variableDeclarator.getBegin().map(p->p.line).orElse(-1);
        this.name = variableDeclarator.getName().toString();
    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

}
