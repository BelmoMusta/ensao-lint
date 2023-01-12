package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.VariableWrapper;

import com.github.javaparser.ast.body.EnumDeclaration;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EnumerationVisitors extends VoidVisitorAdapter<Set<VariableWrapper>> {
	    @Override
	    public void visit(EnumDeclaration enumDeclaration, Set<VariableWrapper> arg) {
		  enumDeclaration.getEntries().forEach(e->arg.add(new VariableWrapper(e.getName()))); 	    
	      super.visit(enumDeclaration, arg);
	    }
}
