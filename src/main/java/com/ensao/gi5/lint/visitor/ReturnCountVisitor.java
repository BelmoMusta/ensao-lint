package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.stmt.ReturnStmt;
import java.util.List;

public class ReturnCountVisitor extends VoidVisitorAdapter<List<RuleCountWrapper>> {
    private List<RuleCountWrapper> methodReturnCounts;
    public ReturnCountVisitor(List<RuleCountWrapper> methodReturnCounts){
        this.methodReturnCounts = methodReturnCounts;
    }
    @Override
    public void visit(MethodDeclaration n, List<RuleCountWrapper> arg) {
        super.visit(n, arg);
        int returnCount = n.findAll(ReturnStmt.class).size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        methodReturnCounts.add(new RuleCountWrapper(returnCount ,line));
    }
}