package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.Set;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LocalVariableVisitor extends VoidVisitorAdapter<Set<ElementWrapper>> {

	@Override
	public void visit(MethodDeclaration n, Set<ElementWrapper> localVariableWrappers) {
		final BlockStmt body = n.getBody().get();
		final List<VariableDeclarator> localVars = body.findAll(VariableDeclarator.class);
		for (VariableDeclarator variableDeclarator : localVars) {
			localVariableWrappers.add(new ElementWrapper(variableDeclarator.getNameAsString(),
					variableDeclarator.getBegin().map(p -> p.line).orElse(-1)));
		}
		super.visit(n, localVariableWrappers);
	}

}
