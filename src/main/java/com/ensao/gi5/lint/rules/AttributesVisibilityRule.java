package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class AttributesVisibilityRule extends Rule {

	public AttributesVisibilityRule() {
		super(Constantes.LINT_REG_013, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final Set<FieldDeclaration> list = new LinkedHashSet<>();
		compilationUnit.accept(new AttributeVisitor(), list);
		list.forEach(f -> {
			if (!f.isPrivate() && !f.isPublic() && !f.isProtected()) {
				final Violation violation = ViolationFactory.createViolation(getId(), f.toString(),
						compilationUnit.getFileName(), f.getBegin().map(p -> p.line).orElse(-1));
				addViolation(violation);
			}
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
