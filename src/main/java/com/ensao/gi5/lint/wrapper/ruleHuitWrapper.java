package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class ruleHuitWrapper {
	 private final String nom;
	    private final int ligne;

	    public ruleHuitWrapper(SimpleName simpleName) {
	        this.nom = simpleName.asString();
	        this.ligne = simpleName.getBegin().map(begin -> begin.line).orElse(0);
	    }
	    public ruleHuitWrapper(FieldDeclaration fieldDeclaration) {
	        this.nom = fieldDeclaration.getVariable(0).getNameAsString();
	        this.ligne = fieldDeclaration.getBegin().map(begin -> begin.line).orElse(0);
	    }

	    public String getNom() {
	        return nom;
	    }

	    public int getLigne() {
	        return ligne;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ruleHuitWrapper that = (ruleHuitWrapper) o;
	        return ligne == that.ligne && Objects.equals(nom, that.nom);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(nom, ligne);
	    }
}
