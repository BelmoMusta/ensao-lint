package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;


public class ClassAttributeWrapper {

    private final String name;
    private final int line;


    public ClassAttributeWrapper(FieldDeclaration fieldDeclaration) {
        this.name = fieldDeclaration.getVariable(0).getNameAsString();
        this.line = fieldDeclaration.getBegin().map(p->p.line).orElse(-1);

    }
    public ClassAttributeWrapper(String name, int line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {return name;}
    public int getLine() {return line;}
}