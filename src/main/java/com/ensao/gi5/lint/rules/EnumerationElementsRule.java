package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.EnumerationElementsVisitor;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class EnumerationElementsRule extends Rule{

	public EnumerationElementsRule() {
		super(Constantes.LINT_REG_007,Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final Set<ElementWrapper> enumElements = new LinkedHashSet<>();
		compilationUnit.accept(new EnumerationElementsVisitor(), enumElements);
		enumElements.stream().filter(e->!e.getName().matches("^[A-Z_]+$")).forEach(e->{
			final Violation violation=ViolationFactory.createViolation(getId(), e.getName(), compilationUnit.getFileName(), e.getLine());
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
