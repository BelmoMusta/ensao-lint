package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.AnonymousClassVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class AnonymousClassRule extends Rule {

	public AnonymousClassRule() {
		super(Constantes.LINT_REG_009, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<NameWrapper> anonymousClass = new ArrayList<>();
		compilationUnit.accept(new AnonymousClassVisitor(), anonymousClass);
		
		anonymousClass.forEach( t -> {
			String description = String.format("It is better to replace the anonymous instantiation of '%s' with a lambda expression", t.name());
			Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(), t.line());
			addViolation(violation); 
		});
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
