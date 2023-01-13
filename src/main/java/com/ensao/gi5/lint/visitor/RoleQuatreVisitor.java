package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleQuatreWrapper;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleQuatreVisitor extends VoidVisitorAdapter<Set<RoleQuatreWrapper>> {
    @Override
    public void visit(com.github.javaparser.ast.body.FieldDeclaration n, Set<RoleQuatreWrapper> arg) {
        super.visit(n, arg);
        arg.add(new RoleQuatreWrapper(n));
    }
}
