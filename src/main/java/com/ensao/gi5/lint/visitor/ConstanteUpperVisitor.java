package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LowUpperWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ConstanteUpperVisitor extends VoidVisitorAdapter<Set<LowUpperWrapper>> {
    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<LowUpperWrapper> arg) {
        if(fieldDeclaration.isFinal()) {
            fieldDeclaration.getVariables().forEach(e-> arg.add(new LowUpperWrapper(e.getName())));
            super.visit(fieldDeclaration, arg);
        }

    }
}
