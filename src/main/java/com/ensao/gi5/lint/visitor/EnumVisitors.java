package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LowUpperWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class EnumVisitors extends VoidVisitorAdapter<Set<LowUpperWrapper>> {
    @Override
    public void visit(EnumDeclaration enumDeclaration, Set<LowUpperWrapper> arg) {
        enumDeclaration.getEntries().forEach(e->arg.add(new LowUpperWrapper(e.getName())));
        super.visit(enumDeclaration, arg);
    }
}
