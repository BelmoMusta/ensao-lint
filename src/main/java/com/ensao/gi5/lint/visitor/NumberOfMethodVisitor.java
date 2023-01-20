package com.ensao.gi5.lint.visitor;

import java.util.Map;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NumberOfMethodVisitor extends VoidVisitorAdapter<Map<NameWrapper, Long>> {

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Map<NameWrapper, Long> arg) {
		if (!n.isInterface()) {
			Long number = n.getMembers().stream().filter(BodyDeclaration::isMethodDeclaration).count();
			String className = n.getNameAsString();
			int line = Utils.getLine(n.getBegin());
			arg.put(new NameWrapper(className, line), number);
		}

		super.visit(n, arg);
	}

}
