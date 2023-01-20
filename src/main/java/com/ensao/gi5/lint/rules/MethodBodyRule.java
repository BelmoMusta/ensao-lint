package com.ensao.gi5.lint.rules;

import java.util.LinkedHashMap;
import java.util.Map;

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

		Map<NameWrapper, Integer> methodBodyLength = new LinkedHashMap<>(); 
		compilationUnit.accept(new MethodBodyVisitor(), methodBodyLength);
		
		
		methodBodyLength.forEach((methodName, bodyLength) -> {
			if(bodyLength > 30) {
				String description = String.format("The body of the method: '%s' must not exceed 30 lines (it has %d lines)", methodName.name(), bodyLength);
				Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(), methodName.line());
				addViolation(violation);
			}
		});
		
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
