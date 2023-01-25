package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class EnumRule extends Rule{

	public EnumRule() {
		super(Constantes.LINT_REG_007, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {


        for (EnumDeclaration enumeration : compilationUnit.findAll2(EnumDeclaration.class)) {
            for (EnumConstantDeclaration constant : enumeration.getEntries()) {
                if (!constant.getNameAsString().toUpperCase().equals(constant.getNameAsString())) {
                    Violation violation = new Violation();
                    violation.setDescription("Enumeration element name should be in uppercase: " + constant.getNameAsString());
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(constant.getBegin().get().line);
                    addViolation(violation);
                }
                if (!constant.getNameAsString().contains("_")) {
                    Violation violation = new Violation();
                    violation.setDescription("Enumeration element name should contain at least one underscore: " + constant.getNameAsString());
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(constant.getBegin().get().line);
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
