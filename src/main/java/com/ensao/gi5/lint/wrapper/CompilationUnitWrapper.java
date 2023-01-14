package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

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

    public List<FieldDeclaration> getAttributes(){
		return compilationUnit.getTypes().stream().
				flatMap(typeDeclaration -> typeDeclaration.getMembers().stream()).
				filter(member -> member instanceof FieldDeclaration).
				map(member -> (FieldDeclaration)member).
				collect(Collectors.toList());
	}


	public List<MethodDeclaration> getMethods(){
		return compilationUnit.getTypes().stream().
				flatMap(typeDeclaration -> typeDeclaration.getMembers().stream()).
				filter(member -> member instanceof MethodDeclaration).
				map(member -> (MethodDeclaration)member).
				collect(Collectors.toList());
	}

	public List<ConstructorDeclaration> getConstructors(){
		return compilationUnit.getTypes().stream().
				flatMap( typeDeclaration -> typeDeclaration.getMembers().stream()).
				filter(member -> member instanceof ConstructorDeclaration).
				map(member -> (ConstructorDeclaration) member).collect(Collectors.toList());
	}

	public int getSizeOfMethod(MethodDeclaration methodDeclaration){
		BlockStmt body = methodDeclaration.getBody().get();
		return body.getEnd().get().line - body.getBegin().get().line;
	}

	public List<Parameter> getParameters(MethodDeclaration method){
		return method.getParameters();
	}

	public List<Parameter> getParameters(CallableDeclaration methodOrConstructor){
		return methodOrConstructor.getParameters();
	}

	public List<VariableDeclarator> getVariables(FieldDeclaration fieldDeclaration){
		return fieldDeclaration.getVariables();
	}

	public List<EnumDeclaration> getEnums(){
		return compilationUnit.findAll(EnumDeclaration.class);
	}

	public List<EnumConstantDeclaration> getElements(EnumDeclaration enumDeclaration){
		return enumDeclaration.getEntries();
	}

	public List<VariableDeclarator> getlocalVariables(MethodDeclaration methodDeclaration){
		return methodDeclaration.findAll(VariableDeclarator.class);
	}
}
