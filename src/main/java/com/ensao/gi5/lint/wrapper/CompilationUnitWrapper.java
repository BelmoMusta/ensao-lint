package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.JavaParser;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CompilationUnitWrapper {
	private  CompilationUnit compilationUnit;
	private  String fileName;
	private final List<Problem> problems = new ArrayList<>();

	public CompilationUnitWrapper(CompilationUnit compilationUnit, String fileName) {
		this.compilationUnit = compilationUnit;
		this.fileName = fileName;
	}

	public CompilationUnitWrapper(File file) {
		try {
			this.compilationUnit = new JavaParser().parse(file).getResult().get();
			this.fileName = file.getName();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public NodeList<ImportDeclaration> getImports() {return compilationUnit.getImports();}
	public List<Node> getChildNodes(){
		return compilationUnit.getChildNodes();
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
