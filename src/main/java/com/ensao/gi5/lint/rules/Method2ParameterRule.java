package com.ensao.gi5.lint.rules;

import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;

public class Method2ParameterRule extends Rule {

	public Method2ParameterRule() {
		super(Constantes.LINT_REG_012, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// Get all methods
		final List<MethodDeclaration> methods = compilationUnit.getMethods();
		for (MethodDeclaration method : methods) {
			// Check if the number of parameters exceeds 2
			if (method.getParameters().size() > 2) {
				Violation violation = new Violation();
				violation.setDescription("Method '" + method.getNameAsString() + "' has too many parameters");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(method.getBegin().get().line);
				addViolation(violation);
			}
		}
		// Get all constructors
		final List<ConstructorDeclaration> constructors = compilationUnit.getConstructors();
		for (ConstructorDeclaration constructor : constructors) {
			// Check if the number of parameters exceeds 2
			if (constructor.getParameters().size() > 2) {
				Violation violation = new Violation();
				violation.setDescription("Constructor '" + constructor.getNameAsString() + "' has too many parameters");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(constructor.getBegin().get().line);
				addViolation(violation);
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
