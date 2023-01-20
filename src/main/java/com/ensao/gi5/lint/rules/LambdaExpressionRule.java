package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.LambdaExpressionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.Expression;

public class LambdaExpressionRule extends Rule {

	public LambdaExpressionRule() {
		super(Constantes.LINT_REG_010, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {

		List<Expression> lambdaExprList = new ArrayList<>();
		compilationUnit.accept(new LambdaExpressionVisitor(), lambdaExprList);

		lambdaExprList.forEach(expr -> {
			String description = getDescription(expr);
			Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(),
					Utils.getLine(expr.getBegin()));
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

	private String getDescription(Expression expr) {
		if (expr.isObjectCreationExpr()) {
			return "Replace the lambda expression with constructor reference if possible";
		} else {
			return "Replace the lambda expression with method reference if possible";
		}
	}

}
