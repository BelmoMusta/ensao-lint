package com.ensao.gi5.lint.visitor;

import java.util.Set;


import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableStartByLowerCaseVisitor extends VoidVisitorAdapter<Set<VariableStartByLowerCaseWrapper>> {
	  @Override
	    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Set<VariableStartByLowerCaseWrapper> arg) {
		  for(FieldDeclaration field: classOrInterfaceDeclaration.getFields())
		    {
			    arg.add(new VariableStartByLowerCaseWrapper(field.getVariable(0).getName()));
		    }
	        super.visit(classOrInterfaceDeclaration, arg);
	    }
}
