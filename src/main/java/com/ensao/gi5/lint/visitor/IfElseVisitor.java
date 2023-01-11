package com.ensao.gi5.lint.visitor;

import java.util.List;
import com.ensao.gi5.lint.wrapper.StmtWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfElseVisitor extends VoidVisitorAdapter<List<StmtWrapper>>  {
	
    @Override
    public void visit(IfStmt ifStmt, List<StmtWrapper> arg) {
    	
    	arg.add(new StmtWrapper(ifStmt.getThenStmt()));
        ifStmt.getElseStmt().ifPresent(st->arg.add(new StmtWrapper(st)));
        super.visit(ifStmt,arg);
    }
}
