package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.AttributWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ConstantWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ConstantRule extends Rule {

	public ConstantRule() {
		 super(Constantes.LINT_REG_005, Level.MEDIUM);

	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
	final	 Set<ConstantWrapper> constants = compilationUnit.getFields()
	                .stream()
	                .filter(fieldDeclaration -> (fieldDeclaration.isStatic() && fieldDeclaration.isFinal()))
	                .map(fieldDeclaration -> new ConstantWrapper(fieldDeclaration.getVariable(0).getName(), fieldDeclaration.getBegin().get().line))
	                .collect(Collectors.toSet());
	        
		for (ConstantWrapper constant : constants) {
            String fieldName = constant.getName();
            if (!fieldName.equals(fieldName.toUpperCase())) {
                Violation violation = new Violation();
                violation.setDescription("Constant '" + fieldName + "' not in upper case");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(constant.getLineNum());
                addViolation(violation);
            } 
            if (!fieldName.contains("_")) {
                Violation violation = new Violation();
                violation.setDescription("Constant '" + fieldName + "' should contain an underscore");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(constant.getLineNum());                
                addViolation(violation);
            }
        }
        
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

}
