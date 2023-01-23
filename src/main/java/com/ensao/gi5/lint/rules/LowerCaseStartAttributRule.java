package com.ensao.gi5.lint.rules;

import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.AttributWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NamingWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class LowerCaseStartAttributRule extends Rule {

	public LowerCaseStartAttributRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
        //instantiate an AttributWrapper, passing in the CompilationUnit from the CompilationUnitWrapper
		AttributWrapper attributWrapper = new AttributWrapper(compilationUnit.getCompilationUnit());
		//iterates through all the fields (attributes) in the class
	    for (FieldDeclaration field : attributWrapper.getFields()) {
            //get the name of the first variable of the field and check if it starts with a lower case
	    	
	        String fieldName = field.getVariable(0).getNameAsString();
	        if (!attributWrapper.startsWithLowerCase(fieldName) && !field.isStatic() && !field.isFinal()) {
	            Violation violation = new Violation();
	            violation.setDescription("Attribut '" + fieldName + "' does not start with an lowercase letter");
	            violation.setFileName(compilationUnit.getFileName());
	            addViolation(violation);
	        }
	    }
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
