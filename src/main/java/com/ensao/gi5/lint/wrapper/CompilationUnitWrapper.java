package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.LambdaExpr;
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
	public List<LambdaExpr> getLambdaExpressions() {
		// Find all lambda expressions in the class
		List<LambdaExpr> lambdas = compilationUnit.findAll(LambdaExpr.class);

		// Filter the list of lambdas to include only those that can be replaced with method references
		return lambdas.stream()
				.filter(lambda -> lambda.getBody().isBlockStmt() || lambda.getBody().isExpressionStmt())
				.collect(Collectors.toList());
	}
	public List<MethodDeclaration> getMethods() {
		List<MethodDeclaration> methods = new ArrayList<>();
		// Traverse the compilation unit's node structure and retrieve all the method declarations
		compilationUnit.findAll(MethodDeclaration.class).forEach(methods::add);
		return methods;
	}
	public List<EnumDeclaration> getEnums() {
		return compilationUnit.getTypes()
				.stream()
				.filter(type -> type instanceof EnumDeclaration)
				.map(type -> (EnumDeclaration) type)
				.collect(Collectors.toList());
	}

}
