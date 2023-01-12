package com.ensao.gi5.lint.visitor;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstantNameVisitor extends VoidVisitorAdapter<Set<NameWrapper>> {
	
	Function<VariableDeclarator, NameWrapper> toNameWrapper = field -> new NameWrapper(field.getNameAsString(),
			Utils.getLine(field.getBegin()));

	@Override
	public void visit(FieldDeclaration n, Set<NameWrapper> arg) {
		if (n.isStatic() && n.isFinal()) {
			n.getVariables().stream()
							.map(toNameWrapper)
							.collect(Collectors.toCollection(() -> arg));
		}

		super.visit(n, arg);
	}

	@Override
	public void visit(EnumConstantDeclaration n, Set<NameWrapper> arg) {
		String name = n.getNameAsString();
		int line = Utils.getLine(n.getBegin()); 
		arg.add(new NameWrapper(name, line));
		
		super.visit(n, arg);
	}
	
	

}
