package com.ensao.gi5.lint.visitor;

import java.util.List;


import com.ensao.gi5.lint.wrapper.VariableWrapper;

import com.github.javaparser.ast.body.EnumDeclaration;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EnumerationVisitors extends VoidVisitorAdapter<List<VariableWrapper>> {
	    @Override
	    public void visit(EnumDeclaration enumDeclaration, List<VariableWrapper> arg) {
		  enumDeclaration.getEntries().forEach(e->arg.add(new VariableWrapper(e.getName()))); 	    
	      super.visit(enumDeclaration, arg);
	    }
}
