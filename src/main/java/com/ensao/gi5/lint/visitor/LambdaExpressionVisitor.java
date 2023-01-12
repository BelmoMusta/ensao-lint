package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LambdaExpressionVisitor extends VoidVisitorAdapter<List<Expression>> {

	@Override
	public void visit(LambdaExpr n, List<Expression> arg) {		
		n.getExpressionBody().ifPresent(arg::add);
		
		super.visit(n, arg);
	}

	
	

}
