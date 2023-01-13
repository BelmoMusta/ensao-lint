package com.ensao.gi5.lint.visitor;

import java.util.List;



import com.ensao.gi5.lint.wrapper.VariableWrapper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstantsInUpperCaseVisitors extends VoidVisitorAdapter<List<VariableWrapper>> {
	
	  @Override
	    public void visit(FieldDeclaration fieldDeclaration, List<VariableWrapper> arg) {		  
		  if(fieldDeclaration.isFinal()) {
			  fieldDeclaration.getVariables().forEach(e-> arg.add(new VariableWrapper(e.getName())));
			  super.visit(fieldDeclaration, arg);
		  }
		
	    }


}
