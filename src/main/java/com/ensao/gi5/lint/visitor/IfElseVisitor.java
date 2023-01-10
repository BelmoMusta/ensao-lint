package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.IfElseWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class IfElseVisitor extends VoidVisitorAdapter<List<IfElseWrapper>> {

    @Override
    public void visit(IfStmt ifStmt,List<IfElseWrapper> arg){
        arg.add(new IfElseWrapper(ifStmt.getThenStmt()));
        ifStmt.getElseStmt().ifPresent(st->arg.add(new IfElseWrapper(st)));
        super.visit(ifStmt,arg);
    }
}
