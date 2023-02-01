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

public class AttributesRule extends Rule {

	public AttributesRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<FieldDeclaration> attributes = new LinkedHashSet<FieldDeclaration>();
		compilationUnit.accept(new AttributeVisitor(), attributes);
		Set<FieldDeclaration> normalAttributes = attributes.stream().filter(a -> !a.isStatic() || !a.isFinal())
				.collect(Collectors.toSet());
		normalAttributes.stream().filter(a -> !a.getVariable(0).getNameAsString().matches("[a-z].*")).forEach(a -> {
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
