package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.BooleanExpressionsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

public class BooleanExpressionsRule extends Rule {
	final static String BOOLEAN_PATTERN = "(([\\w\\d\\s.])+(==|!=|<|>|>=|<=)[\\w\\d\\s.]+([&\\|]{2})?)+";

	public BooleanExpressionsRule() {
		super(Constantes.LINT_REG_006, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		Set<StatementWrapper> statements = new LinkedHashSet<StatementWrapper>();
		compilationUnit.accept(new BooleanExpressionsVisitor(), statements);
//		statements.stream().filter(s -> {
//			System.out.println(s.getLine());
//			String[] statementString = s.getStatement().toString().split("&&|\\|\\|");
//			return statementString.length % 2 != 0;
//		}).forEach(c -> {
//			final Violation violation = ViolationFactory.createViolation(getId(), c.getStatement().toString(),
//					compilationUnit.getFileName(), c.getLine());
//			addViolation(violation);
//		});
		for (StatementWrapper s : statements) {
			System.out.println(s.getStatement().toString());    
			Matcher matcher = Pattern.compile(BOOLEAN_PATTERN).matcher(s.getStatement().toString());
			if (matcher.find()) {
				if (matcher.group().split("&&|\\|\\|").length%2!=2) {
					final Violation violation = ViolationFactory.createViolation(getId(), s.getStatement().toString(),
							compilationUnit.getFileName(), s.getLine());
					addViolation(violation);
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
