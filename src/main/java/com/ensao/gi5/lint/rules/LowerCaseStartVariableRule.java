package com.ensao.gi5.lint.rules;

import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;

public class LowerCaseStartVariableRule extends Rule {

	public LowerCaseStartVariableRule() {
		super(Constantes.LINT_REG_003, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// Filtering out all members that are not variableDeclarators, and mapping each
		// variableDeclarator to a VariableWrapper instance
		final Set<VariableWrapper> locallyDeclaredVariables = compilationUnit.getVariables()
				.stream()
			    .filter(member -> member instanceof VariableDeclarator)
				.map(variableDeclarator -> new VariableWrapper(variableDeclarator.getName(),variableDeclarator.getBegin().get().line))
				.collect(Collectors.toSet());
		// Iterate through the set of variableWrapper instances, checking if the name of
		// each variable starts with a lowercase letter.
		// If it doesn't create a violation and set the violation description and line
		// number accordingly

		for (VariableWrapper localVar : locallyDeclaredVariables) {
			if (!Character.isLowerCase(localVar.getName().charAt(0))) {
				Violation violation = new Violation();
				violation.setDescription(
						"Local variable '" + localVar.getName() + "' does not begin with a lowercase letter.");
				violation.setFileName(compilationUnit.getFileName());
				violation.setLine(localVar.getLineNum());
				addViolation(violation);
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
