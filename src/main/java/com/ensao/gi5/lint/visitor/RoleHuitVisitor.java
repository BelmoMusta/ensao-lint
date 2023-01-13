package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleHuitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleHuitVisitor extends VoidVisitorAdapter<Set<RoleHuitWrapper>> {
    @Override
    public void visit(MethodCallExpr n, Set<RoleHuitWrapper> arg) {
        arg.add(new RoleHuitWrapper(n.getName()));
        super.visit(n, arg);
    }
}
