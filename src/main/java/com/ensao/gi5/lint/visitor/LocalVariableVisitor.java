package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.ensao.gi5.lint.wrapper.TypeNamingWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class LocalVariableVisitor extends VoidVisitorAdapter<Set<LocalVariableWrapper>> {
    @Override
    public void visit(VariableDeclarator n, Set<LocalVariableWrapper> arg) {
        arg.add(new LocalVariableWrapper(n));
        super.visit(n, arg);
    }
}
