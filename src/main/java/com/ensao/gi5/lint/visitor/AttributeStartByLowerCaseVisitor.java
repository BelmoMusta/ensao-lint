package com.ensao.gi5.lint.visitor;

import java.util.Set;


import com.ensao.gi5.lint.wrapper.AttributeStartByLowerCaseWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AttributeStartByLowerCaseVisitor extends VoidVisitorAdapter<Set<AttributeStartByLowerCaseWrapper>> {
	  @Override
	    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Set<AttributeStartByLowerCaseWrapper> arg) {
		  for(FieldDeclaration field: classOrInterfaceDeclaration.getFields())
		    {
			    arg.add(new AttributeStartByLowerCaseWrapper(field.getVariable(0).getName()));
		    }
	        super.visit(classOrInterfaceDeclaration, arg);
	    }
}
