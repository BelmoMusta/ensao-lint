package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.MethodParameterWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ParameterVisitor extends VoidVisitorAdapter<List<MethodParameterWrapper>> {
    @Override
    public void visit(MethodDeclaration n, List<MethodParameterWrapper> arg) {
        super.visit(n, arg);
        int parameterCount = n.getParameters().size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        arg.add(new MethodParameterWrapper(n.getNameAsString(), parameterCount,line));
    }

    @Override
    public void visit(ConstructorDeclaration n, List<MethodParameterWrapper> arg) {
        super.visit(n, arg);
        int parameterCount = n.getParameters().size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        arg.add(new MethodParameterWrapper(n.getNameAsString(), parameterCount,line));
    }
}
