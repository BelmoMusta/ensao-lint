package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompilationUnitWrapper {
	private final CompilationUnit compilationUnit;
	private final String fileName;
	private final List<Problem> problems = new ArrayList<>();

	public CompilationUnitWrapper(CompilationUnit compilationUnit, String fileName) {
		this.compilationUnit = compilationUnit;
		this.fileName = fileName;
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

	// get all fields within a compilation unit
	public List<FieldDeclaration> getFields() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(type -> type.getMembers().stream())
				.filter(member -> member instanceof FieldDeclaration)
				.map(member -> (FieldDeclaration) member)
				.collect(Collectors.toList());
	}

	// get all methods within a compilation unit
	public List<MethodDeclaration> getMethods() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(type -> type.getMembers().stream())
				.filter(member -> member instanceof MethodDeclaration)
				.map(member -> (MethodDeclaration) member)
				.collect(Collectors.toList());
	}

	// get all enums within a compilation unit
	public List<EnumDeclaration> getEnums() {
		return compilationUnit.getTypes()
				.stream()
				.filter(type -> type instanceof EnumDeclaration)
				.map(type -> (EnumDeclaration) type)
				.collect(Collectors.toList());
	}
}
