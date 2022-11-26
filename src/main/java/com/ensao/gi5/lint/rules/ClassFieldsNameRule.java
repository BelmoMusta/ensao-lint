package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.ClassFieldsNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class ClassFieldsNameRule extends Rule{
	
	private static final Pattern CLASS_FIEDS_NAME_PATTERN = Pattern.compile("^[a-z][a-zA-Z\\d_$]*"); 

	public ClassFieldsNameRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
 	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
	
		List<NameWrapper> fiedsName = new ArrayList<>();
		compilationUnit.accept(new ClassFieldsNameVisitor(), fiedsName);
		
		fiedsName.stream()
					.filter(field -> !CLASS_FIEDS_NAME_PATTERN.matcher(field.name()).matches())
					.forEach(field -> {
						String description = "The first letter of this field : '" + field.name() + "' must be in lowercase";
						Violation violation = Utils.createNewInstanceOfViolation(description, field.name(), field.line());
						addViolation(violation); 
					});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
