package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.Set;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
public class LocalVariableVisitor extends ElementVisitor{
	
	@Override
	public void visit(MethodDeclaration n, Set<ElementWrapper> localVariableWrappers) {
        final BlockStmt body = n.getBody().get();
		final List<VariableDeclarator> localVars = body.findAll(VariableDeclarator.class);
		for (VariableDeclarator variableDeclarator : localVars) {
			localVariableWrappers.add(new LocalVariableWrapper(variableDeclarator));	
		}
		super.visit(n, localVariableWrappers);
	}

}
