package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.ConstantesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class ConstantesRule extends Rule {

	public ConstantesRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<ElementWrapper> constantes = new LinkedHashSet<ElementWrapper>();
		compilationUnit.accept(new ConstantesVisitor(), constantes);
		constantes.stream().filter(c->!c.getName().equals(c.getName().toUpperCase())).forEach(c->{
			final Violation violation = ViolationFactory.createViolation(getId(), c.getName(),
					compilationUnit.getFileName(), c.getLine());
			addViolation(violation);
		});

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
