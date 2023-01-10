package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.TypeNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class TypeNameRule extends Rule {

	public TypeNameRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final Set<ElementWrapper> typeNameWrappers = new LinkedHashSet<>();
		compilationUnit.accept(new TypeNameVisitor(), typeNameWrappers);
		for (ElementWrapper typeNameWrapper : typeNameWrappers) {
			String typeName = typeNameWrapper.getName();
			if (!Character.isUpperCase(typeName.charAt(0))) {
				final Violation violation = new Violation();
				violation.setDescription("Type Name does not start with UpperCase '" + typeNameWrapper + "'");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(typeNameWrapper.getLine());
				addViolation(violation);
			}

			if (typeName.contains("_")) {
				final Violation violation = new Violation();
				violation.setDescription("Type Name contains underscore '" + typeNameWrapper + "'");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(typeNameWrapper.getLine());
				addViolation(violation);
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
