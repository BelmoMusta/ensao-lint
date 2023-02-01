package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.AnonymousInstantiationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;

public class AnonymousInstantiationRule extends Rule{
	
	public AnonymousInstantiationRule() {
		super(Constantes.LINT_REG_009, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<ElementWrapper> anonymousInstantiations = new ArrayList<ElementWrapper>();
		compilationUnit.accept(new AnonymousInstantiationVisitor(), anonymousInstantiations);
	    anonymousInstantiations.forEach(e->{
	    	final Violation violation=ViolationFactory.createViolation(getId(), e.getName(), compilationUnit.getFileName(), e.getLine());
	    	addViolation(violation);
	    });
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
