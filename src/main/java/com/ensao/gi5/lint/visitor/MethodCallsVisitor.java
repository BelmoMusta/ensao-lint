package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodCallsVisitor extends VoidVisitorAdapter<Set<String>> {

	@Override
    public void visit(MethodCallExpr n, Set<String> arg) {
        super.visit(n, arg);
        arg.add(n.getNameAsString());
    }
}
