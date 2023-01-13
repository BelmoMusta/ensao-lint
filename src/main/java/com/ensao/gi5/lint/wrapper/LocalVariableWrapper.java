package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;


public class LocalVariableWrapper {

    private final int line;
    private final String name;


    public LocalVariableWrapper(SimpleName simpleName) {
        this.name = simpleName.asString();
        this.line = simpleName.getBegin().map(begin -> begin.line).orElse(0);
    }
    public String getName() {return name;}
    public int getLine() {return line;}
}