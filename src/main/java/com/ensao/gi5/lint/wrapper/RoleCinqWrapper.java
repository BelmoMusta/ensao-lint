package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

public class RoleCinqWrapper {
    private final String nom;
    private final int ligne;

    public RoleCinqWrapper(SimpleName simpleName) {
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
