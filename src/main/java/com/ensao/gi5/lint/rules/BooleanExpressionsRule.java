package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.BooleanExpressionsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.BinaryExpr;

public class BooleanExpressionsRule extends Rule {

	public BooleanExpressionsRule() {
		super(Constantes.LINT_REG_006, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<BinaryExpr> bExps = new LinkedHashSet<BinaryExpr>();
		compilationUnit.accept(new BooleanExpressionsVisitor(), bExps);
		bExps.stream().filter(b ->b.toString().split("&&|\\|\\|").length>2).forEach(c -> {
			final Violation violation = ViolationFactory.createViolation(getId(), c.toString().toString(),
					compilationUnit.getFileName(), c.getBegin().map(p->p.line).orElse(-1));
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
