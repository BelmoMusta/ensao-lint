package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

	public boolean methodUsed(MethodDeclaration method) {
		AtomicBoolean usedMethod = new AtomicBoolean(false);
		method.findAll(MethodCallExpr.class)
				.forEach(methodCallExpr -> {
					if (methodCallExpr.resolve().equals(method)) {
						usedMethod.set(true);
					}
				});
		return usedMethod.get();
	}

	public List<MethodDeclaration> getMethods() {
		List<MethodDeclaration> methods = new ArrayList<>();
		compilationUnit.findAll(MethodDeclaration.class).forEach(methods::add);
		return methods;
	}

	public List<TypeDeclaration<?>> getTypes() {
		return compilationUnit.getTypes();
	}

	public List<ClassOrInterfaceDeclaration> getClasses() {
		return compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
	}

}
