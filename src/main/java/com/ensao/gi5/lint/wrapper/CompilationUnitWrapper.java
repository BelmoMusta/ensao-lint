package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompilationUnitWrapper {
	private final CompilationUnit compilationUnit;
	private final String fileName;
	private final List<Problem> problems = new ArrayList<>();

	public CompilationUnitWrapper(CompilationUnit compilationUnit, String fileName) {
		this.compilationUnit = compilationUnit;
		this.fileName = fileName;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public NodeList<ImportDeclaration> getImports() {
		return compilationUnit.getImports();
	}

	public <A> void accept(VoidVisitor<A> v, A arg) {
		compilationUnit.accept(v, arg);
	}

	public String getFileName() {
		return fileName;
	}

	public void addProblems(Collection<Problem> problems) {
		this.problems.addAll(problems);
	}

	public List<Problem> getProblems() {
		return problems;
	}

	public List<VariableDeclarator> getVariables() {
		// Get all VariableDeclarators
		List<VariableDeclarator> variableDeclarators = compilationUnit.findAll(VariableDeclarator.class);

		// Iterate through the VariableDeclarators and remove the ones that are used as
		// class fields
		Iterator<VariableDeclarator> iter = variableDeclarators.iterator();
		while (iter.hasNext()) {
			Node parent = iter.next().getParentNode().get();
			if (!(parent instanceof VariableDeclarationExpr)) {
				iter.remove();
			}
		}
		return variableDeclarators;
	}

	public List<FieldDeclaration> getFields() {
		// get all class fields
		List<FieldDeclaration> fields = compilationUnit.findAll(FieldDeclaration.class);
		return fields;
	}

	public List<String> getTypes() {
		// get the classes interfaces enums annotations
		return compilationUnit.getTypes().stream().map(TypeDeclaration::getNameAsString).collect(Collectors.toList());
	}

	public List<MethodDeclaration> getMethods() {
		return compilationUnit.findAll(MethodDeclaration.class);
	}
	
	public boolean isMethodUsed(MethodDeclaration method) {
		// Check if a method is used
		return compilationUnit.findAll(MethodCallExpr.class).stream()
				.anyMatch(methodCallExpr -> methodCallExpr.getNameAsString().equals(method.getNameAsString()));
	}
	
	public List<ConstructorDeclaration> getConstructors() {
	    return compilationUnit.findAll(ConstructorDeclaration.class);
	}

}
