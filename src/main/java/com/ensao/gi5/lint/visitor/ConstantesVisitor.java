package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ConstantesVisitor extends ElementVisitor{
	
	@Override
	public void visit(FieldDeclaration fd,Set<ElementWrapper> arg) {
		if (fd.isFinal() && fd.isStatic()) {
			arg.add(new AttributeWrapper(fd));
		}
		super.visit(fd, arg);
	}
		
}
