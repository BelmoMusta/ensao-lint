package com.ensao.gi5.lint.rules;

import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Methods20maxRule extends Rule {

	public Methods20maxRule() {
		super(Constantes.LINT_REG_011, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// Get a list of methods and check if this list size is greater than 20
		 final List<MethodDeclaration> listMethods = compilationUnit.getMethods();
         if(listMethods.size()>20){
             Violation violation = new Violation();
             violation.setDescription("This Class contain more than 20 methods");
             violation.setFileName(compilationUnit.getFileName());
             addViolation(violation);
     }
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
