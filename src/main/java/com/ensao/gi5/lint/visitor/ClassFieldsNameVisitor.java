package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassFieldsNameVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

	Function<VariableDeclarator, NameWrapper> mapper = field -> new NameWrapper(field.getNameAsString(),
			Utils.getLine(field.getBegin()));

	@Override
	public void visit(FieldDeclaration n, List<NameWrapper> arg) {
		n.getVariables().stream().map(mapper).collect(Collectors.toCollection(() -> arg));

		super.visit(n, arg);
	}

}
