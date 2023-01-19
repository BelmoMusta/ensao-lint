package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariableNameWrapper extends Wrapper{
    public LocalVariableNameWrapper(VariableDeclarator variableDeclarator) {
        super(variableDeclarator.getName().toString(),variableDeclarator.getBegin().map(p->p.line).orElse(-1));
    }
}
