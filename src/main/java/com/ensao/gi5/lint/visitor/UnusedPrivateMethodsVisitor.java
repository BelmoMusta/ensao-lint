package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnusedPrivateMethodsVisitor extends VoidVisitorAdapter<Void> {
    private Set<String> usedMethods = new HashSet<>();
    private List<RuleWrapper> unusedMethods;
    public UnusedPrivateMethodsVisitor(List<RuleWrapper> unusedMethods){
        this.unusedMethods=unusedMethods;
    }
    @Override
    public void visit(MethodDeclaration method, Void arg) {
        if(method.isPrivate() && !usedMethods.contains(method.getDeclarationAsString())) {
            unusedMethods.add(new RuleWrapper(method.getDeclarationAsString() ,method.getRange().isPresent()?method.getRange().get().begin.line:0));
        }
        usedMethods.add(method.getDeclarationAsString());
        super.visit(method, arg);
    }

}
