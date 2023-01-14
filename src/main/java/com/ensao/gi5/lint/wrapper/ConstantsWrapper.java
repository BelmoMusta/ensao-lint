package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;

public class ConstantsWrapper {

    private final String name;
    private final int line;


    public ConstantsWrapper( FieldDeclaration FieldDeclaration) {
        this.name = FieldDeclaration.getVariable(0).getNameAsString();
        this.line = FieldDeclaration.getBegin().map(p->p.line).orElse(-1);

    }
    public ConstantsWrapper(String name, int line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {return name;}
    public int getLine() {return line;}
}