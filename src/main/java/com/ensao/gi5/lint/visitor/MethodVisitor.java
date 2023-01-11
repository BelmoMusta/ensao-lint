package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.MethodWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class MethodVisitor extends VoidVisitorAdapter<List<MethodWrapper>> {
    @Override
    public void visit(MethodDeclaration n, List<MethodWrapper> arg) {
        arg.add(new MethodWrapper(n));
        super.visit(n, arg);
    }
}
