package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.Optional;

import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BooleanExpressionVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

	@Override
	public void visit(IfStmt n, List<NameWrapper> arg) {
		String name = "The if condition";
		getNameWrapper(n.getCondition(), name).ifPresent(t -> arg.add(t));

		super.visit(n, arg);
	}

	@Override
	public void visit(ForStmt n, List<NameWrapper> arg) {
		String name = "The for statement condition";
		Optional<Expression> compare = n.getCompare();
		if (!compare.isEmpty()) {
			getNameWrapper(compare.get(), name).ifPresent(t -> arg.add(t));
		}

		super.visit(n, arg);
	}

	@Override
	public void visit(DoStmt n, List<NameWrapper> arg) {
		String name = "The do statement condition";
		getNameWrapper(n.getCondition(), name).ifPresent(t -> arg.add(t));

		super.visit(n, arg);
	}

	@Override
	public void visit(ConditionalExpr n, List<NameWrapper> arg) {
		String name = "The ternary operator condition";
		getNameWrapper(n.getCondition(), name).ifPresent(t -> arg.add(t));

		super.visit(n, arg);
	}

	@Override
	public void visit(WhileStmt n, List<NameWrapper> arg) {
		String name = "The while statement condition";
		getNameWrapper(n.getCondition(), name).ifPresent(t -> arg.add(t));

		super.visit(n, arg);
	}

	@Override
	public void visit(ReturnStmt n, List<NameWrapper> arg) {
		String name = "The return statement condition";
		Optional<Expression> expression = n.getExpression();
		if (!expression.isEmpty()) {
			getNameWrapper(expression.get(), name).ifPresent(t -> arg.add(t));
		}

		super.visit(n, arg);
	}

	private Optional<NameWrapper> getNameWrapper(Expression expr, String name) {
		if (expr.isBinaryExpr()) {
			if (checkIfContainsMoreThanTwoOperands(expr)) {
				int line = expr.getBegin().map(p -> p.line).orElse(-1);
				NameWrapper nameWrapper = new NameWrapper(name, line);
				return Optional.of(nameWrapper);
			}
		}

		return Optional.empty();
	}

	private boolean checkIfContainsMoreThanTwoOperands(Expression expr) {
		BinaryExpr binaryExpr = (BinaryExpr) expr;
		Expression left = binaryExpr.getLeft();
		Expression right = binaryExpr.getRight();

		if (left instanceof BinaryExpr || right instanceof BinaryExpr)
			return true;

		return false;

	}

}
