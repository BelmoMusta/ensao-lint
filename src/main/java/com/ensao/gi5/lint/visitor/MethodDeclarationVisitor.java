package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class MethodDeclarationVisitor extends VoidVisitorAdapter<Set<MethodDeclaration>> {
    @Override
    public void visit(MethodDeclaration n, Set<MethodDeclaration> arg) {
        arg.add(n);
        super.visit(n, arg);
    }
}
