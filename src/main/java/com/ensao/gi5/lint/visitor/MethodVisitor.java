package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter<List<MethodDeclaration>>{

	@Override
	public void visit(MethodDeclaration md,List<MethodDeclaration> arg) {
		arg.add(md);
		super.visit(md, arg);
	}
}
