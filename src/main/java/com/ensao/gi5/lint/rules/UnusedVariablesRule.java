package com.ensao.gi5.lint.rules;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.visitor.UsedVariablesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class UnusedVariablesRule extends Rule {

	public UnusedVariablesRule() {
		super(Constantes.LINT_REG_016, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<MethodDeclaration> list = new LinkedList<>();
		compilationUnit.accept(new MethodVisitor(), list);
		list.forEach(m -> {
			final Set<String> usedVariables = new HashSet<>();
			final UsedVariablesVisitor usedVariablesVisitor = new UsedVariablesVisitor();
			usedVariablesVisitor.visit( m, usedVariables);
			m.findAll(VariableDeclarator.class).forEach(v -> {
				if (!usedVariables.contains(v.getNameAsString())) {
					final Violation violation = ViolationFactory.createViolation(getId(), v.getNameAsString(),
							compilationUnit.getFileName(), v.getBegin().map(p -> p.line).orElse(-1));
					addViolation(violation);
				}
			});
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
