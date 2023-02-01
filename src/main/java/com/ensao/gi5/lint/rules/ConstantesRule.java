package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ConstantesRule extends Rule {

	public ConstantesRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<FieldDeclaration> attributes = new LinkedHashSet<FieldDeclaration>();
		compilationUnit.accept(new AttributeVisitor(), attributes);
		Set<FieldDeclaration> constantes = attributes.stream().filter(a ->a.isStatic() && a.isFinal())
				.collect(Collectors.toSet());
		constantes.stream().filter(a -> !a.getVariable(0).getNameAsString().matches("[A-Z_]+")).forEach(a -> {
			String name = a.getVariable(0).getNameAsString();
			int line = a.getBegin().map(p -> p.line).orElse(-1);
			final Violation violation = ViolationFactory.createViolation(getId(), name, compilationUnit.getFileName(),
					line);
			addViolation(violation);
		});

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
