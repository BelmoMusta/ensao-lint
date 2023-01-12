package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodBodyVisitor extends VoidVisitorAdapter<Set<MethodDeclaration>>{

	@Override
	public void visit(MethodDeclaration md,Set<MethodDeclaration> arg) {
		arg.add(md);
	}
}
