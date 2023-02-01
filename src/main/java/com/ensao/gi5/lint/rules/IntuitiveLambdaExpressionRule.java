package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.LambdaExpressionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class IntuitiveLambdaExpressionRule extends Rule{

	public IntuitiveLambdaExpressionRule() {
		super(Constantes.LINT_REG_010, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<ElementWrapper> intuitiveLambdaExpressions=new LinkedList<>();
		compilationUnit.accept(new LambdaExpressionVisitor(), intuitiveLambdaExpressions);
		intuitiveLambdaExpressions.forEach(ile->{
			final Violation violation=ViolationFactory.createViolation(getId(), ile.getName(), compilationUnit.getFileName(), ile.getLine());
			addViolation(violation);
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
