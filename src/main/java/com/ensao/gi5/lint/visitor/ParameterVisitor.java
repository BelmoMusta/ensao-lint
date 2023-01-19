package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ParameterVisitor extends VoidVisitorAdapter<List<RuleCountWrapper>> {
    @Override
    public void visit(MethodDeclaration n, List<RuleCountWrapper> arg) {
        super.visit(n, arg);
        int parameterCount = n.getParameters().size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        arg.add(new RuleCountWrapper(parameterCount,line));
    }

    @Override
    public void visit(ConstructorDeclaration n, List<RuleCountWrapper> arg) {
        super.visit(n, arg);
        int parameterCount = n.getParameters().size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        arg.add(new RuleCountWrapper(parameterCount,line));
    }
}
