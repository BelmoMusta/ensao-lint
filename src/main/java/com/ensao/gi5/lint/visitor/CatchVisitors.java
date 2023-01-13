package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.StatementWrapper;

import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CatchVisitors extends VoidVisitorAdapter<List<StatementWrapper>> {
	
	@Override
	public void visit(CatchClause catchClause, List<StatementWrapper> arg) {		
				
			arg.add(new StatementWrapper(catchClause.getBody()));
		
		   super.visit(catchClause, arg);
	}

}
