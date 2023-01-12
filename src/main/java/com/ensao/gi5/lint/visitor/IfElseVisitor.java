package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;


public class IfElseVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private List<RuleWrapper> missingIfBrackets ;
    private List<RuleWrapper> missingElseBrackets ;
    public IfElseVisitor(List<RuleWrapper> missingIfBrackets,List<RuleWrapper>missingElseBrackets){
        this.missingIfBrackets = missingIfBrackets;
        this.missingElseBrackets = missingElseBrackets;
    }
    @Override
    public void visit(IfStmt n, List<RuleWrapper> arg) {
        if (!n.getThenStmt().isBlockStmt()) {
            missingIfBrackets.add(new RuleWrapper(n.toString() ,n.getRange().isPresent()?n.getRange().get().begin.line:0));
        }
        if (n.getElseStmt().isPresent() && !n.getElseStmt().get().isBlockStmt()) {
            missingElseBrackets.add(new RuleWrapper(n.toString() ,n.getRange().isPresent()?n.getRange().get().end.line:0));
        }

        super.visit(n, arg);
    }
}
