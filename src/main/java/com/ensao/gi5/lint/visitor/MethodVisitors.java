package com.ensao.gi5.lint.visitor;


import java.util.List;


import com.ensao.gi5.lint.wrapper.MethodWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitors extends VoidVisitorAdapter<List<MethodWrapper>>{

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<MethodWrapper> arg) { 
    	

    	classOrInterfaceDeclaration.getMethods().forEach(method ->
    	{ 
    		int countLine = method.getEnd().map(p->p.line).orElse(0) - method.getBegin().map(p->p.line).orElse(0);
    		int parameterCount = method.getParameters().size();
    	    int returnCount = (int) method.getBody().get().getStatements().stream().filter(Statement::isReturnStmt).count();
    		int throwCount = (int) method.getBody().get().getStatements().stream().filter(Statement::isThrowStmt).count();
    		int line = method.getBegin().map(p ->p.line).orElse(-1);
    	    arg.add(new MethodWrapper(countLine, parameterCount, returnCount,throwCount,line));
    	});          

       
        super.visit(classOrInterfaceDeclaration, arg);
    }


}