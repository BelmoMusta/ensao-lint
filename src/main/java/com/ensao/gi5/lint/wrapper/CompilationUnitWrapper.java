package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
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
    
    //implementation de getMethods() de la classe OperandExpression
	public List<MethodDeclaration> getMethods() {
		List<MethodDeclaration> methods = new ArrayList<>();
		compilationUnit.findAll(MethodDeclaration.class).forEach(methods::add);
		return methods;
	}
	//implementation de getTypes() de la classe CheckType
	@SuppressWarnings("rawtypes")
	public List<TypeDeclaration> getTypes() {
		List<TypeDeclaration> types = new ArrayList<>();
		compilationUnit.findAll(TypeDeclaration.class).forEach(types::add);
		return types;
	}
	//implementation de findAll(Class<ClassOrInterfaceDeclaration> class1) de la classe AttributeRule
	public List<ClassOrInterfaceDeclaration> findAll1(Class<ClassOrInterfaceDeclaration> class1) {
		List<ClassOrInterfaceDeclaration> declarations = new ArrayList<>();
		compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(declarations::add);
		return declarations;
	}

	public List<EnumDeclaration> findAll2(Class<EnumDeclaration> class1) {
		List<EnumDeclaration> enums = new ArrayList<>();
		compilationUnit.findAll(EnumDeclaration.class).forEach(enums::add);
		return enums;
	}
}
