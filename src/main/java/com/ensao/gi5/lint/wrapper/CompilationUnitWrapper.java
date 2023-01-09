package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitor;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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


	public NodeList<ImportDeclaration> getImports() {return compilationUnit.getImports();}

	public List<MethodDeclaration> getMethods() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(type -> type.getMembers().stream())
				.filter(member -> member instanceof MethodDeclaration)
				.map(member -> (MethodDeclaration) member)
				.collect(Collectors.toList());
	}

	public List<ConstructorDeclaration> getConstructors() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(type -> type.getMembers().stream())
				.filter(member -> member instanceof ConstructorDeclaration)
				.map(member -> (ConstructorDeclaration) member)
				.collect(Collectors.toList());
	}

	public List<FieldDeclaration> getFields() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(type -> type.getMembers().stream())
				.filter(member -> member instanceof FieldDeclaration)
				.map(member -> (FieldDeclaration) member)
				.collect(Collectors.toList());
	}

	public List<FieldDeclaration> getConstants() {
		return compilationUnit.getTypes()
				.stream()
				.flatMap(t -> t.getMembers().stream())
				.filter(m -> m instanceof FieldDeclaration)
				.map(m -> (FieldDeclaration) m)
				.filter(f -> f.isFinal() && f.isStatic())
				.collect(Collectors.toList());
	}

	public List<TypeDeclaration> getTypeDeclaration() {
		return compilationUnit.getTypes()
				.stream()
				.filter(type -> type instanceof ClassOrInterfaceDeclaration ||
				type instanceof AnnotationDeclaration ||
				type instanceof EnumDeclaration)
				.map(TypeDeclaration.class::cast)
				.collect(Collectors.toList());
	}


	public <A> void accept(VoidVisitor<A> v, A arg) {
		compilationUnit.accept(v, arg);}
	
	public String getFileName() {
		return fileName;
	}
	
    public void addProblems(Collection<Problem> problems){
        this.problems.addAll(problems);
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
