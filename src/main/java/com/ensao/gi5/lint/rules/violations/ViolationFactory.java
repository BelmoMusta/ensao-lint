package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.constantes.Constantes;

public class ViolationFactory {

	public static Violation createViolation(String ruleID, String source, String fileName,int line) {
		final Violation violation=new Violation();
		violation.setFileName(fileName);
		violation.setLine(line);
		switch (ruleID) {
		case Constantes.LINT_REG_002:
			violation.setDescription("Type name should start with an uppercase at '" + source + "'");
			break;
		case Constantes.LINT_REG_003:
			violation.setDescription("local variables should start with lowercase at '" + source + "'");
			break;
		case Constantes.LINT_REG_004:
			violation.setDescription("class's attributes should start with lowercase at '" + source + "'");
			break;
		case Constantes.LINT_REG_005:
			violation.setDescription("constantes should be in uppercase at '" + source + "'");
			break;
		case Constantes.LINT_REG_006:
			violation.setDescription("Boolean expressions should have a max of 2 operands at '" + source + "'");
			break;
		case Constantes.LINT_REG_007:
			violation.setDescription("Enumeration element should in uppercase separated by _   at '" + source + "'");
			break;
		case Constantes.LINT_REG_008:
			violation.setDescription("A metho body should not bypass 30 line at'" + source + "'");
			break;
		case Constantes.LINT_REG_009:
			violation.setDescription("Anonymous class instantiation found, consider using a lambda expression instead at line");
			break;
		default:
			break;
		}
		return violation;
	}
}
