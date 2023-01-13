package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfElseVisitors extends VoidVisitorAdapter<List<StatementWrapper>> {
	
	@Override
	public void visit(IfStmt ifStmt, List<StatementWrapper> arg) {		
				
			arg.add(new StatementWrapper(ifStmt));
		
		   super.visit(ifStmt, arg);
	}

}
