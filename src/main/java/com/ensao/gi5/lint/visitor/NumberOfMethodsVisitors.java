package com.ensao.gi5.lint.visitor;

import java.util.List;


import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NumberOfMethodsVisitors extends VoidVisitorAdapter<List<VariableWrapper>>{

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<VariableWrapper> arg) { 
    	

    	if(classOrInterfaceDeclaration.getMethods().size() > 20) {
    		arg.add(new VariableWrapper(classOrInterfaceDeclaration.getName()));
    	}
    	         

       
        super.visit(classOrInterfaceDeclaration, arg);
    }
}