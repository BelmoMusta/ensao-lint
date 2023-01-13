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

//MethodDeclaration for return type of methods //try to use resolve method to get the parameters passed
//FieldDeclaration to get the type of attributs
//TypeDeclaration to get the type of class or interface
//ClassOrInterfaceDeclaration to get construtor
//ifStmt for if and else block

public class CompilationUnitWrapper {
	private final CompilationUnit compilationUnit;
	private final String fileName;
	private final List<Problem> problems = new ArrayList<>();

	public CompilationUnitWrapper(CompilationUnit compilationUnit, String fileName) {
		this.compilationUnit = compilationUnit;
		this.fileName = fileName;
	}
	//CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration<?>> types, ModuleDeclaration module)
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


	//RULE 4 : LINT_REG_004
	//RULE 13: LINT_REG_013 Les attributs de classe doivent avoir une visibilité déclarée
    public List<FieldDeclaration> getAttributes(){
		return compilationUnit.getTypes().stream().
				flatMap(typeDeclaration -> typeDeclaration.getMembers().stream()).
				filter(member -> member instanceof FieldDeclaration).
				map(member -> (FieldDeclaration)member).
				collect(Collectors.toList());
	}

	//RULE 11 : LINT_REG_011 Le nombre de méthodes ne doit pas dépasser 20 méthodes déclarées par classe
	//RULE 17:   LINT_REG_017 Les méthodes privées non utilisées sont à supprimer
	public List<MethodDeclaration> getMethods(){
		List<TypeDeclaration> typeDeclarations = compilationUnit.getTypes().stream().collect(Collectors.toList());
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

	//Get the number of lines of a method
	//RULE 8 : LINT_REG_008 Le corps d'une méthode ne doit pas dépasser 30 lignes
	public int getSizeOfMethod(MethodDeclaration methodDeclaration){
		BlockStmt body = methodDeclaration.getBody().get();
		return body.getRange().get().end.line - body.getRange().get().begin.line;
	}

	//get number of parameters of a method or ctor
	public List<Parameter> getParameters(MethodDeclaration method){
		return method.getParameters();
	}

	public List<Parameter> getParameters(CallableDeclaration methodOrConstructor){
		return methodOrConstructor.getParameters();
	}

	public List<VariableDeclarator> getVariables(FieldDeclaration fieldDeclaration){
		return fieldDeclaration.getVariables();
	}

	//RULE 007
	public List<EnumDeclaration> getEnums(){
		return compilationUnit.findAll(EnumDeclaration.class);
	}

	public List<EnumConstantDeclaration> getElements(EnumDeclaration enumDeclaration){
		return enumDeclaration.getEntries();
	}

	//Local variables used in methods
	public List<VariableDeclarator> getlocalVariables(MethodDeclaration methodDeclaration){
		return methodDeclaration.findAll(VariableDeclarator.class);
	}
}
