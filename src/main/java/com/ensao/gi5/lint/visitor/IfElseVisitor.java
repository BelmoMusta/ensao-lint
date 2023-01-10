package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class IfElseVisitor extends VoidVisitorAdapter<List<GeneralStatementWrapper>> {

    @Override
    public void visit(IfStmt ifStmt,List<GeneralStatementWrapper> arg){
        arg.add(new GeneralStatementWrapper(ifStmt.getThenStmt()));
        ifStmt.getElseStmt().ifPresent(st->arg.add(new GeneralStatementWrapper(st)));
        super.visit(ifStmt,arg);
    }
}
