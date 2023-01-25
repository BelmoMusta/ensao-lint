package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class AttributeRule extends Rule{

	public AttributeRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
	      for (ClassOrInterfaceDeclaration clazz : compilationUnit.findAll1(ClassOrInterfaceDeclaration.class)) {
	            for (FieldDeclaration field : clazz.getFields()) {
	                for (VariableDeclarator variable : field.getVariables()) {
	                    if (!Character.isLowerCase(variable.getNameAsString().charAt(0))) {
	                        Violation violation = new Violation();
	                        violation.setDescription("Class attribute name should start with a lowercase letter: " + variable.getNameAsString());
	                        violation.setFileName(compilationUnit.getFileName());
	                        violation.setLine(variable.getBegin().get().line);
	                        addViolation(violation);
	                    }
	                    }
	                }
	            }
	           
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
}
