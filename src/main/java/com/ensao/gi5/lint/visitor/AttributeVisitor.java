package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AttributeVisitor extends VoidVisitorAdapter<Set<FieldDeclaration>> {

	@Override
	public void visit(FieldDeclaration fd, Set<FieldDeclaration> arg) {
		arg.add(fd);
		super.visit(fd, arg);
	}
}
