package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LambdaExpressionVisitor extends VoidVisitorAdapter<List<ElementWrapper>> {

	@Override
	public void visit(LambdaExpr le, List<ElementWrapper> arg) {
		if (le.getBody().isExpressionStmt()) {
			ExpressionStmt expression = (ExpressionStmt) le.getBody();
			if (expression.getExpression() instanceof MethodCallExpr) {
				int line = le.getBegin().map(p -> p.line).orElse(-1);
				arg.add(new ElementWrapper(null, line));
			}
		}
		super.visit(le, arg);
	}
}
