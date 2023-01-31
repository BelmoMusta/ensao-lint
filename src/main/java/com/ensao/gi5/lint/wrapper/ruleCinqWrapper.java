package com.ensao.gi5.lint.wrapper;
import com.github.javaparser.ast.expr.SimpleName;
public class ruleCinqWrapper {
	private final String nom;
    private final int ligne;

    public ruleCinqWrapper(SimpleName simpleName) {
        this.nom = simpleName.asString();
        this.ligne = simpleName.getBegin().map(begin -> begin.line).orElse(-1);
    }

    public String getNom() {
        return nom;
    }

    public int getLigne() {
        return ligne;
    }
}
