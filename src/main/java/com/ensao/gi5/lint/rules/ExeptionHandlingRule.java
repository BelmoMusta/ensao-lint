package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;

public class ExeptionHandlingRule extends Rule {

	public ExeptionHandlingRule() {
		super(Constantes.LINT_REG_015, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<MethodDeclaration> list = new LinkedList<>();
		compilationUnit.accept(new MethodVisitor(), list);
		list.forEach(m -> {
			m.findAll(CatchClause.class).forEach(catchClause -> {
				if (!catchClause.getBody().getStatements().stream().anyMatch(s -> s instanceof ExpressionStmt
						&& ((ExpressionStmt) s).getExpression().toString().contains("Logger"))) {
					final Violation violation = ViolationFactory.createViolation(getId(), null,
							compilationUnit.getFileName(), catchClause.getBegin().map(p -> p.line).orElse(-1));
					addViolation(violation);
				}
			});
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
