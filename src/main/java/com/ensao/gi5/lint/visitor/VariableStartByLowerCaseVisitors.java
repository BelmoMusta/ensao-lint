package com.ensao.gi5.lint.visitor;



import java.util.List;


import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;

import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableStartByLowerCaseVisitors extends VoidVisitorAdapter<List<VariableStartByLowerCaseWrapper>> {
	
	  
	  @Override
	  public void visit(VariableDeclarationExpr variableDeclarationExpr, List<VariableStartByLowerCaseWrapper> arg ) {		 
		  variableDeclarationExpr.getVariables().forEach(e-> arg.add(new VariableStartByLowerCaseWrapper(e.getName())));

		  super.visit(variableDeclarationExpr, arg);
	  }
	 

}
