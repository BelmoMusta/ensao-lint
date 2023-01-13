package com.ensao.gi5.lint.wrapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.SimpleName;

public class AttributWrapper {
	private String name;
	private Set<Modifier> modifiers;
	private int line;
	private List<FieldDeclaration> fields;

	public AttributWrapper(SimpleName name, NodeList<Modifier> modifiers) {
        this.name = name.getIdentifier();
        this.modifiers = modifiers.stream().collect(Collectors.toSet());
    }

	public String getName() {
		return name;
	}

	public Set<Modifier> getModifiers() {
		return modifiers;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public AttributWrapper(final CompilationUnit compilationUnit) {
		this.fields = compilationUnit.findAll(FieldDeclaration.class);
	}

	public boolean startsWithLowerCase(String name) {
		return Character.isLowerCase(name.charAt(0));
	}

	public List<FieldDeclaration> getFields() {
		return fields;
	}
}
