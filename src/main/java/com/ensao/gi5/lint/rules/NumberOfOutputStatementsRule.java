package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.visitor.ReturnVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class NumberOfOutputStatementsRule extends Rule {

	public NumberOfOutputStatementsRule() {
		super(Constantes.LINT_REG_014, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<MethodDeclaration> list = new LinkedList<>();
		compilationUnit.accept(new MethodVisitor(), list);
		list.forEach(m -> {
			List<Integer> l = new LinkedList<>();
			final ReturnVisitor returnVisitor = new ReturnVisitor();
			returnVisitor.visit(m, l);
			if (l.size() > 1) {
				final Violation violation = ViolationFactory.createViolation(getId(), m.getNameAsString(),
						compilationUnit.getFileName(),
			             m.getBegin()
						  .map(p -> p.line)
						  .orElse(-1));
				addViolation(violation);
			}
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
