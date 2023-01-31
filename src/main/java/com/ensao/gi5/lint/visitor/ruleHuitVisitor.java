package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ruleHuitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ruleHuitVisitor extends VoidVisitorAdapter<Set<ruleHuitWrapper>> {
    @Override
    public void visit(MethodCallExpr n, Set<ruleHuitWrapper> arg) {
        arg.add(new ruleHuitWrapper(n.getName()));
        super.visit(n, arg);
    }
}
