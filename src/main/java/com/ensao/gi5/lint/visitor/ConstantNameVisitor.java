package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstantNameVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

	@Override
	public void visit(FieldDeclaration n, List<NameWrapper> arg) {

		if (n.isStatic() && n.isFinal()) {
			n.getVariables().stream()
							.map(field -> new NameWrapper(field.getNameAsString(),
									field.getBegin().map(p -> p.line).orElse(-1)))
							.collect(Collectors.toCollection(() -> arg));
		}

		super.visit(n, arg);
	}

	@Override
	public void visit(EnumConstantDeclaration n, List<NameWrapper> arg) {
		
		String name = n.getNameAsString();
		int line = n.getBegin().map(p -> p.line).orElse(-1); 
		arg.add(new NameWrapper(name, line));
		
		super.visit(n, arg);
	}
	
	

}
