package com.ensao.gi5.lint.rules;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.MethodCallsVisitor;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.visitor.TypeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class UnusedPrivateMethodsRule extends Rule {

	public UnusedPrivateMethodsRule() {
		super(Constantes.LINT_REG_017, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = new LinkedList<>();
		compilationUnit.accept(new TypeVisitor(), classOrInterfaceDeclarations);
		classOrInterfaceDeclarations.forEach(t -> {
			final MethodVisitor methodVisitor = new MethodVisitor();
			final List<MethodDeclaration> methodDeclarations = new LinkedList<>();
			methodVisitor.visit(t, methodDeclarations);
			final List<MethodDeclaration> privateMethodDeclarations = methodDeclarations.stream()
					.filter(MethodDeclaration::isPrivate).collect(Collectors.toList());
			System.out.println(privateMethodDeclarations);
			final Set<String> usedMethods = new HashSet<>();
			final MethodCallsVisitor methodCallsVisitor = new MethodCallsVisitor();
			methodCallsVisitor.visit(t, usedMethods);
			System.out.println(usedMethods);
			privateMethodDeclarations.forEach(pmd -> {
				if (!usedMethods.contains(pmd.getNameAsString())) {
					final Violation violation = ViolationFactory.createViolation(getId(), pmd.getNameAsString(),
							compilationUnit.getFileName(), pmd.getBegin().map(p -> p.line).orElse(-1));
					addViolation(violation);
				}
			});

		});

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
