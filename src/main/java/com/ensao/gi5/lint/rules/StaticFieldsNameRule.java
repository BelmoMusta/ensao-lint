package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.ClassStaticFieldsNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class StaticFieldsNameRule extends Rule {
	
	private static final Pattern STATIC_FIELD_PATTERN = Pattern.compile("^[A-Z][A-Z_]*");

	public StaticFieldsNameRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<NameWrapper> staticFields = new ArrayList<>(); 
		
		compilationUnit.accept(new ClassStaticFieldsNameVisitor(), staticFields);
		
		staticFields.stream()
						.filter(field -> !STATIC_FIELD_PATTERN.matcher(field.name()).matches())
						.forEach(field -> {
							String description ="The constant name : '" + field.name() + "'  should be all uppercase with words separated by underscores ('_'). " ;
							Violation violation = Utils.createNewInstanceOfViolation(description, 
									compilationUnit.getFileName(), field.line());
							addViolation(violation); 
						});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
