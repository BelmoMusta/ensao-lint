package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class AttributeNameRule extends Rule {

	public AttributeNameRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<ElementWrapper> attributes = new LinkedHashSet<ElementWrapper>();
		compilationUnit.accept(new AttributeVisitor(), attributes);
		attributes.stream().filter(a -> !a.getName().matches("[a-z].*")).forEach(a -> {
			final Violation violation = ViolationFactory.createViolation(getId(), a.getName(),
					compilationUnit.getFileName(), a.getLine());
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
