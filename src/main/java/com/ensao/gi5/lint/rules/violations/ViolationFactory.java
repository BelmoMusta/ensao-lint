package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.constantes.Constantes;

public class ViolationFactory {

	public static Violation createViolation(String ruleID, String source, String fileName,int line) {
		final Violation violation=new Violation();
		violation.setFileName(fileName);
		violation.setLine(line);
		switch (ruleID) {
		case Constantes.LINT_REG_002:
			violation.setDescription("Type name should start with an uppercase '" + source + "'");
			break;
		case Constantes.LINT_REG_003:
			violation.setDescription("local variables should start with lowercase '" + source + "'");
			break;
		case Constantes.LINT_REG_004:
			violation.setDescription("class's attributes should start with lowercase '" + source + "'");
			break;
		case Constantes.LINT_REG_005:
			violation.setDescription("constantes should be in uppercase '" + source + "'");
			break;
		case Constantes.LINT_REG_006:
			violation.setDescription("local varible does not start with lower case '" + source + "'");
			break;
		case Constantes.LINT_REG_007:
			violation.setDescription("local varible does not start with lower case '" + source + "'");
			break;
		default:
			break;
		}
		return violation;
	}
}
