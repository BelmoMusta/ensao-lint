package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class ClassAttributsNameWrapper extends Wrapper{


    private final String className;


    public ClassAttributsNameWrapper(FieldDeclaration fieldDeclaration) {
        super(fieldDeclaration.getVariable(0).getNameAsString(),fieldDeclaration.getBegin().map(p->p.line).orElse(-1));
        this.className = Utils.findClassName(fieldDeclaration);

    }

    public String getClassName() {
        return className;
    }
}
