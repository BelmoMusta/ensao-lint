package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ClassMethodsVisitor extends VoidVisitorAdapter<List<RuleCountWrapper>> {
    private int count;
    private List<RuleCountWrapper> countMethods;
    public ClassMethodsVisitor(List<RuleCountWrapper> countMethods){
        this.countMethods=countMethods;
    }
    @Override
    public void visit(MethodDeclaration n, List<RuleCountWrapper> arg) {
        super.visit(n, arg);
        count++;
        if(count>20) {
            countMethods.add(new RuleCountWrapper(count, 1));
        }
    }
}