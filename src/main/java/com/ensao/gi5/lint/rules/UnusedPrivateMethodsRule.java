package com.ensao.gi5.lint.rules;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class UnusedPrivateMethodsRule extends Rule {

	public UnusedPrivateMethodsRule() {
		super(Constantes.LINT_REG_017, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// Get private methods
		final List<MethodDeclaration> methods = compilationUnit.getMethods()
				.stream()
				.filter(method -> method.isPrivate())
				.collect(Collectors.toList());

		for (MethodDeclaration method : methods) {
			// Check if it's used
			if (!compilationUnit.isMethodUsed(method)) {
				final Violation violation = new Violation();
				violation.setDescription("This private method '" + method.getNameAsString() + "' is not used");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(method.getBegin().get().line);
				addViolation(violation);
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
