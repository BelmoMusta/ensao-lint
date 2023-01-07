package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassStaticFieldsNameVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

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

}
