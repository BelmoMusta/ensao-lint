package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.MethodBodyVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class MethodBodyRule extends Rule {

	public MethodBodyRule() {
		super(Constantes.LINT_REG_008, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {

		List<NameWrapper> methodNames = new ArrayList<>(); 
		
		compilationUnit.accept(new MethodBodyVisitor(), methodNames);
		
		methodNames.forEach(methodName -> {
			String description = String.format("The body of the method: '%s' must not exceed 30 lines", methodName.name());
			Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(), methodName.line());
			addViolation(violation);
		});
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
