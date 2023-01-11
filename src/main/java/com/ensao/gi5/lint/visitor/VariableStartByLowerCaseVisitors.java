package com.ensao.gi5.lint.visitor;



import java.util.Set;

import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;

import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableStartByLowerCaseVisitors extends VoidVisitorAdapter<Set<VariableStartByLowerCaseWrapper>> {
	
	  
	  @Override
	  public void visit(VariableDeclarationExpr es, Set<VariableStartByLowerCaseWrapper> arg ) {
		  es.getVariables().forEach(e-> arg.add(new VariableStartByLowerCaseWrapper(e.getName())));
		  super.visit(es, arg);
	  }
	 

}
