package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class UnusedPrivateMethodsVisitor extends VoidVisitorAdapter<Void> {
    private Set<String> usedMethods = new HashSet<>();
    private final Hashtable<String,Integer> unusedMethods ;
    public UnusedPrivateMethodsVisitor(Hashtable<String,Integer> unusedMethods){
        this.unusedMethods=unusedMethods;
    }
    @Override
    public void visit(MethodDeclaration method, Void arg) {
        if(method.isPrivate() && !usedMethods.contains(method.getDeclarationAsString())) {
            unusedMethods.put(method.getDeclarationAsString() ,method.getRange().isPresent()?method.getRange().get().begin.line:0);
        }
        usedMethods.add(method.getDeclarationAsString());
        super.visit(method, arg);
    }

}
