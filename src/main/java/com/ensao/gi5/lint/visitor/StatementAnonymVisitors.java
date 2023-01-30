package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class StatementAnonymVisitors extends VoidVisitorAdapter<List<StatementWrapper>> {
	
	@Override
	public void visit(ObjectCreationExpr n, List<StatementWrapper> arg) {		
		if(n.getAnonymousClassBody().isPresent()) {			
			arg.add(new StatementWrapper(n.getTypeAsString(), n.getBegin().map(p ->p.line).orElse(-1)));
		}
		super.visit(n, arg);
	}

}
