package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.AttributeStartByLowerCaseWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AttributeStartByLowerCaseVisitors extends VoidVisitorAdapter<List<AttributeStartByLowerCaseWrapper>> {
	  @Override
	    public void visit(FieldDeclaration field, List<AttributeStartByLowerCaseWrapper> arg) {
		
		 	if(!field.isFinal()) {
			    	arg.add(new AttributeStartByLowerCaseWrapper(field.getVariable(0).getName()));
			    	
		        }
		    
	        super.visit(field, arg);
	    }
}
