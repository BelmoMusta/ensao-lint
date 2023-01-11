package com.ensao.gi5.lint.visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.ensao.gi5.lint.wrapper.TypeNameWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NameVisitor extends VoidVisitorAdapter<Map<String, Set<ElementWrapper>>> {
	// class annotation enumeration
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Map<String, Set<ElementWrapper>> arg) {
		arg.putIfAbsent(Constantes.LINT_REG_002, new HashSet<ElementWrapper>());
		arg.get(Constantes.LINT_REG_002).add(new TypeNameWrapper(n));
		super.visit(n, arg);
	}

	@Override
	public void visit(AnnotationDeclaration n, Map<String, Set<ElementWrapper>> arg) {
		arg.putIfAbsent(Constantes.LINT_REG_002, new HashSet<ElementWrapper>());
		arg.get(Constantes.LINT_REG_002).add(new TypeNameWrapper(n));
		super.visit(n, arg);
	}

	@Override
	public void visit(EnumDeclaration n, Map<String, Set<ElementWrapper>> arg) {
		arg.putIfAbsent(Constantes.LINT_REG_002, new HashSet<ElementWrapper>());
		arg.get(Constantes.LINT_REG_002).add(new TypeNameWrapper(n));
		super.visit(n, arg);
	}

	// attributes && constantes
	@Override
	public void visit(FieldDeclaration fd, Map<String, Set<ElementWrapper>> arg) {
		if (fd.isFinal() && fd.isStatic()) {
			arg.putIfAbsent(Constantes.LINT_REG_005, new HashSet<ElementWrapper>());
			arg.get(Constantes.LINT_REG_005).add(new AttributeWrapper(fd));
		} else {
			arg.putIfAbsent(Constantes.LINT_REG_004, new HashSet<ElementWrapper>());
			arg.get(Constantes.LINT_REG_004).add(new AttributeWrapper(fd));
		}
		super.visit(fd, arg);
	}

	// local variables
	@Override
	public void visit(MethodDeclaration n, Map<String, Set<ElementWrapper>> arg) {
		final BlockStmt body = n.getBody().get();
		final List<VariableDeclarator> localVars = body.findAll(VariableDeclarator.class);
		arg.putIfAbsent(Constantes.LINT_REG_003, new HashSet<ElementWrapper>());
		for (VariableDeclarator variableDeclarator : localVars) {
			arg.get(Constantes.LINT_REG_003).add(new LocalVariableWrapper(variableDeclarator));
		}
		super.visit(n, arg);
	}

}
