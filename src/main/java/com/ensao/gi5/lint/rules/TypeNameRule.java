package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.TypeNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class TypeNameRule extends Rule {

	public TypeNameRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final Set<ElementWrapper> names = new LinkedHashSet<>();
		compilationUnit.accept(new TypeNameVisitor(), names);
		names.stream()
			 .filter(n -> Character.isLowerCase(n.getName().charAt(0)))
			 .forEach(n -> {
				 final Violation violation = ViolationFactory.createViolation(
						 getId(),
						 n.getName(),
						 compilationUnit.getFileName(),
						 n.getLine());
				 addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
