package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.ConstantNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class ConstantNameRule extends Rule {
	
	private static final Pattern CONSTANT_NAME_PATTERN = Pattern.compile("^[A-Z][A-Z_]*");

	protected ConstantNameRule(String id, Level level) {
		super(id, level);
	}
	
	public ConstantNameRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}


	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<NameWrapper> constants = new LinkedHashSet<>(); 
		compilationUnit.accept(new ConstantNameVisitor(), constants);
		
		constants.stream()
						.filter(field -> !CONSTANT_NAME_PATTERN.matcher(field.name()).matches())
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
