package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.LowerCaseWrapper;

import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class LowerCaseVisitors extends VoidVisitorAdapter<Set<LowerCaseWrapper>> {
    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, Set<LowerCaseWrapper> arg ) {
        variableDeclarationExpr.getVariables().forEach(e-> arg.add(new LowerCaseWrapper(e.getName())));
        super.visit(variableDeclarationExpr, arg);
    }
}
