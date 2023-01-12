package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class MethodLengthVisitor extends VoidVisitorAdapter<List<RuleCountWrapper>> {
    private List<RuleCountWrapper> methods;
    public MethodLengthVisitor(List<RuleCountWrapper> methods){
        this.methods=methods;
    }
    @Override
    public void visit(MethodDeclaration n, List<RuleCountWrapper> arg) {
        super.visit(n, arg);
        int methodLength = n.getEnd().get().line - n.getBegin().get().line + 1;
        if (methodLength >= 30) {
            methods.add(new RuleCountWrapper(n.getNameAsString(),n.getBegin().get().line));
        }
    }
}