package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleCinqWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleCinqVisitor extends VoidVisitorAdapter<Set<RoleCinqWrapper>> {
    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<RoleCinqWrapper> roleCinqWrappers) {
        super.visit(fieldDeclaration, roleCinqWrappers);
        fieldDeclaration.getVariables().forEach(variableDeclarator -> {
            roleCinqWrappers.add(new RoleCinqWrapper(variableDeclarator.getName()));
        });
    }
}
