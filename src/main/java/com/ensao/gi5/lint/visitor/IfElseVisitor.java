package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;


public class IfElseVisitor extends VoidVisitorAdapter<Void> {
    private Hashtable<String,Integer> missingIfBrackets ;
    private Hashtable<String,Integer> missingElseBrackets ;
    public IfElseVisitor(Hashtable<String,Integer> missingIfBrackets,Hashtable<String,Integer> missingElseBrackets){
        this.missingIfBrackets = missingIfBrackets;
        this.missingElseBrackets = missingElseBrackets;
    }
    @Override
    public void visit(IfStmt n, Void arg) {
        if (!n.getThenStmt().isBlockStmt()) {
            missingIfBrackets.put(n.toString() ,n.getRange().isPresent()?n.getRange().get().begin.line:0);
        }
        if (n.getElseStmt().isPresent() && !n.getElseStmt().get().isBlockStmt()) {
            missingElseBrackets.put(n.toString() ,n.getRange().isPresent()?n.getRange().get().end.line:0);
        }

        super.visit(n, arg);
    }
}
