package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.NominationWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class LocalvarVisitors extends VoidVisitorAdapter<Set<String>> {
    @Override
    public void visit(VariableDeclarationExpr n, Set<String> arg) {
        arg.add(n.getVariables().toString());
        super.visit(n, arg);

    }
}
