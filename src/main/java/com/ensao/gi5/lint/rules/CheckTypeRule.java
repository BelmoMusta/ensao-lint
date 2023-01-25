package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

public class CheckTypeRule extends Rule{

	public CheckTypeRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
		        for (@SuppressWarnings("rawtypes") TypeDeclaration type : compilationUnit.getTypes()) {
		            if (!Character.isUpperCase(type.getNameAsString().charAt(0))) {
		                Violation violation = new Violation();
		                violation.setDescription("Type name should start with an uppercase letter: " + type.getNameAsString());
		                violation.setFileName(compilationUnit.getFileName());
		                violation.setLine(type.getBegin().get().line);
		                addViolation(violation);
		            }
		            if (type.getNameAsString().contains("_")) {
		                Violation violation = new Violation();
		                violation.setDescription("Type name should not contain an underscore: " + type.getNameAsString());
		                violation.setFileName(compilationUnit.getFileName());
		                violation.setLine(type.getBegin().get().line);
		                addViolation(violation);
		            }
		        }
		        
		    
		}
		

	@Override
	public boolean isActive() {
		return true;
	}

}
