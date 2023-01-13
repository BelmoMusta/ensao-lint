package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableVisitors extends VoidVisitorAdapter<List<VariableWrapper>> {
	
	  
	  @Override
	  public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<VariableWrapper> arg ) {		 
          arg.add(new VariableWrapper(classOrInterfaceDeclaration.getName()));
		  super.visit(classOrInterfaceDeclaration, arg);
	  }
	  @Override
	  public void visit(EnumDeclaration  enumDeclaration , List<VariableWrapper> arg ) {		 
          arg.add(new VariableWrapper(enumDeclaration.getName()));
		  super.visit(enumDeclaration , arg);
	  }
	  @Override
	  public void visit(AnnotationDeclaration  annotationDeclaration , List<VariableWrapper> arg ) {		 
          arg.add(new VariableWrapper(annotationDeclaration.getName()));
		  super.visit(annotationDeclaration , arg);
	  }
	  

}
