package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BooleanExpressionsVisitor extends VoidVisitorAdapter<Set<BinaryExpr>>{
	
	@Override
	public void visit(BinaryExpr be,Set<BinaryExpr> arg) {
		arg.add(be);
	}
	
}
