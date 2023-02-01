package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.IfElseStmtVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class IfElseStatementsRule extends Rule {

	public IfElseStatementsRule() {
		super(Constantes.LINT_REG_018, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<ElementWrapper> list = new LinkedList<>();
		compilationUnit.accept(new IfElseStmtVisitor(), list);
		list.forEach(e -> {
			final Violation violation = ViolationFactory.createViolation(getId(), null, compilationUnit.getFileName(),
					0);
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
