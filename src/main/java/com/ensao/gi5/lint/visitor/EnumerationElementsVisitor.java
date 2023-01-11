package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EnumerationElementsVisitor extends VoidVisitorAdapter<Set<ElementWrapper>> {

	@Override
	public void visit(EnumConstantDeclaration n, Set<ElementWrapper> arg) {
		ElementWrapper elementWrapper = new AttributeWrapper();
		elementWrapper.setName(n.getName().asString());
		elementWrapper.setLine(n.getBegin().map(p -> p.line).orElse(-1));
		arg.add(elementWrapper);
	}
}
