package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class ClassAttributsNameWrapper {

    private final int line;
    private final String name;
    private final String className;


    public ClassAttributsNameWrapper(FieldDeclaration fieldDeclaration) {
        this.line = fieldDeclaration.getBegin().map(p->p.line).orElse(-1);
        this.name = fieldDeclaration.getVariable(0).getNameAsString();
        this.className = Utils.findClassName(fieldDeclaration);

    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}
