package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;

public class EnumerationWrapper {

    private final String name;
    private final int line;


    public EnumerationWrapper( FieldDeclaration FieldDeclaration) {
        this.name = FieldDeclaration.getVariable(0).getNameAsString();
        this.line = FieldDeclaration.getBegin().map(p->p.line).orElse(-1);

    }

    public String getName() {return name;}
    public int getLine() {return line;}
}