package com.ensao.gi5.lint.rules;

import java.util.HashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.MethodBodyVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodBodyRule extends Rule {

	public MethodBodyRule() {
		super(Constantes.LINT_REG_008, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<MethodDeclaration> mds = new HashSet<MethodDeclaration>();
		compilationUnit.accept(new MethodBodyVisitor(), mds);
		mds.stream()
				.filter(m -> m.getEnd().map(p -> p.line).orElse(-1) - m.getBegin().map(p -> p.line).orElse(-1) + 1 > 30)
				.forEach(e -> {
					final Violation violation = ViolationFactory.createViolation(getId(), e.getNameAsString(),
							compilationUnit.getFileName(), e.getBegin().map(p -> p.line).orElse(-1));
					addViolation(violation);
				});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
