package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

public class ClauseIfElseVisitors extends VoidVisitorAdapter<List<StatementWrapper>> {
    @Override
    public void visit(IfStmt s, List<StatementWrapper> arg) {
            arg.add(new StatementWrapper(s.getThenStmt()));
            super.visit(s, arg);
            }

    }
