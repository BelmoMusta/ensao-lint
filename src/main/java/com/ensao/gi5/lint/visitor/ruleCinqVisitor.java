package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ruleCinqWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ruleCinqVisitor extends VoidVisitorAdapter<Set<ruleCinqWrapper>> {
    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<ruleCinqWrapper> roleCinqWrappers) {
        super.visit(fieldDeclaration, roleCinqWrappers);
        fieldDeclaration.getVariables().forEach(variableDeclarator -> {
            roleCinqWrappers.add(new ruleCinqWrapper(variableDeclarator.getName()));
        });
    }
}
