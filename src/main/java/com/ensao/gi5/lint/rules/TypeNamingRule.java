package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.TypeNamingVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypeNameWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeNamingRule extends Rule {

	private static final Pattern JAVA_TYPE_NAME_PATTERN = Pattern.compile("[A-Z][a-zA-Z\\d$]*");

	public TypeNamingRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {

		List<TypeNameWrapper<TypeDeclaration<?>>> typeNames = new ArrayList<>();
		compilationUnit.accept(new TypeNamingVisitors(), typeNames);

		typeNames.stream()
				.filter(type -> !JAVA_TYPE_NAME_PATTERN.matcher(type.getNameWrapper().name()).matches())
				.forEach(type -> {
					String description = "The first letter of: '" + type.getNameWrapper().name()
							+ "' must be capitalized or should not  contains a  '_' character";
					Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(),
							type.getNameWrapper().line());
					addViolation(violation);
				});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
