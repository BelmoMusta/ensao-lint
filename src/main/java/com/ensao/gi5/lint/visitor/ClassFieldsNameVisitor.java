package com.ensao.gi5.lint.visitor;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassFieldsNameVisitor extends VoidVisitorAdapter<Set<NameWrapper>> {

	Function<VariableDeclarator, NameWrapper> toNameWrapper = field -> new NameWrapper(field.getNameAsString(),
			Utils.getLine(field.getBegin()));

	@Override
	public void visit(FieldDeclaration n, Set<NameWrapper> arg) {
		n.getVariables().stream().map(toNameWrapper).collect(Collectors.toCollection(() -> arg));

		super.visit(n, arg);
	}

}
