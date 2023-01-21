package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompilationUnitWrapper {
	private final CompilationUnit compilationUnit;
	private final String fileName;
	private final List<Problem> problems = new ArrayList<>();

	public CompilationUnitWrapper(CompilationUnit compilationUnit, String fileName) {
		this.compilationUnit = compilationUnit;
		this.fileName = fileName;
	}
	
	public NodeList<ImportDeclaration> getImports() {return compilationUnit.getImports();}
        
	public List<ClassOrInterfaceDeclaration> getClassesOrInterfaces() {return compilationUnit.findAll(ClassOrInterfaceDeclaration.class);}
        
	public List<MethodDeclaration> getNumberOfFunctions() {return compilationUnit.findAll(MethodDeclaration.class);}
        public List<ConstructorDeclaration> getFunctionContent(){return compilationUnit.findAll(ConstructorDeclaration.class);}
	
        
        public List<VariableDeclarator> getClassAttribute() {return compilationUnit.findAll(VariableDeclarator.class);}

        public List<EnumDeclaration> getEnums() {return compilationUnit.findAll(com.github.javaparser.ast.body.EnumDeclaration.class);}
        
        public List<FieldDeclaration> getLocalVars() {return compilationUnit.findAll(com.github.javaparser.ast.body.FieldDeclaration.class);}

        
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
