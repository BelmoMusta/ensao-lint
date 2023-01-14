package com.ensao.gi5.lint.wrapper;


import com.github.javaparser.ast.expr.SimpleName;

public class LambdaExpressionWrapper {
    private final String name;
    private final int line;

    public LambdaExpressionWrapper(SimpleName name, int line) {
        this.name = String.valueOf(name);
        this.line = line;
    }
    public int getLine() {
        return line;
    }
    public String getName() {
        return name;
    }


}
