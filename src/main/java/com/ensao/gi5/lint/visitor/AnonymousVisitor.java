package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;

public class AnonymousVisitor extends VoidVisitorAdapter<Void> {
    private Hashtable<String,Integer> anonymousInstanciations ;
    public AnonymousVisitor(Hashtable<String,Integer> anonymousInstanciations){
        this.anonymousInstanciations = anonymousInstanciations;
    }
    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        super.visit(n, arg);
        if (n.getAnonymousClassBody().isPresent()) {
            anonymousInstanciations.put(n.getAnonymousClassBody().toString(),n.getBegin().get().line);
        }
    }
}
