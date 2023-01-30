package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.ConstructorWrapper;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitors extends VoidVisitorAdapter<List<ConstructorWrapper>>{
	  @Override
	    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<ConstructorWrapper> arg) { 
	    	

	    	classOrInterfaceDeclaration.getConstructors().forEach(c ->
	    	{ 
	    		int constructorParamCount = c.getParameters().size();
	    		int line = c.getBegin().map(p ->p.line).orElse(-1);
	    	    arg.add(new ConstructorWrapper(constructorParamCount, line));
	    	});          

	       
	        super.visit(classOrInterfaceDeclaration, arg);
	    }
}
