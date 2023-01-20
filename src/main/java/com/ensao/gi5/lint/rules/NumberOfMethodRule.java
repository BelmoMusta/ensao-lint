package com.ensao.gi5.lint.rules;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.NumberOfMethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class NumberOfMethodRule extends Rule {

	public NumberOfMethodRule() {
		super(Constantes.LINT_REG_011, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {

		Map<NameWrapper, Long> numberOfMethodPerClass = new LinkedHashMap<>();
		compilationUnit.accept(new NumberOfMethodVisitor(), numberOfMethodPerClass);

		numberOfMethodPerClass.forEach((className, number) -> {
			if(number > 20) {
				String description = String.format("The class '%s' must not exceed 20 methods (it has %d methods)",
						className.name(), number);
				Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(), className.line());
				addViolation(violation);				
			}
		});

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
