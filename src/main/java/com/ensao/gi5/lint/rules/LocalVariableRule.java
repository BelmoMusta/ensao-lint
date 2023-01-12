package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class LocalVariableRule extends Rule {

	public LocalVariableRule() {
		super(Constantes.LINT_REG_003, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final Set<ElementWrapper> localVariables = new LinkedHashSet<>();
		compilationUnit.accept(new LocalVariableVisitor(), localVariables);
		localVariables.stream().filter(l->!l.getName().matches("[a-z].*")).forEach(l->{
			final Violation violation = ViolationFactory.createViolation(getId(), l.getName(),
					compilationUnit.getFileName(), l.getLine());
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

	
}
