package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class ImportWrapper extends AbstractWrapper{
	private final String importDeclarationFQN;
	private final String importDeclaration;
	
	
	public ImportWrapper(ImportDeclaration importDeclaration) {
		super(importDeclaration.getBegin().map(p ->p.line).orElse(-1));
		this.importDeclarationFQN = importDeclaration.getNameAsString();
		this.importDeclaration = Utils.convertFQNToSimpleClassName(importDeclaration.getNameAsString());
	}
	
	public ImportWrapper(String importDeclaration) {
		super(0);
		this.importDeclarationFQN = importDeclaration;
		this.importDeclaration = Utils.convertFQNToSimpleClassName(importDeclaration);
	}
	
	public ImportWrapper(NameExpr name) {
		this(name.getNameAsString());
	}
	
	public ImportWrapper(Name name) {
		this(name.asString());
	}
	
	public ImportWrapper(SimpleName name) {
		this(name.asString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }

		if (!(o instanceof ImportWrapper)) {
            return false;
        }
		ImportWrapper that = (ImportWrapper) o;
		return Objects.equals(importDeclaration, that.importDeclaration);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(importDeclaration);
	}
	
	@Override
	public String toString() {
		return importDeclarationFQN;
	}

}
