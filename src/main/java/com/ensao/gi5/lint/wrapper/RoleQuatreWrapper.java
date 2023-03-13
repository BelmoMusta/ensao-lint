package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import java.util.Objects;
public class RoleQuatreWrapper {
    private final String nom;
    private final int ligne;

    public RoleQuatreWrapper(String nom, int ligne) {
        this.nom = nom;
        this.ligne = ligne;
    }
    public String getNom() {
        return nom;
    }

    public int getLigne() {
        return ligne;
    }

    public RoleQuatreWrapper(FieldDeclaration fieldDeclaration){
        this.nom = fieldDeclaration.getVariables().get(0).getNameAsString();
        this.ligne = fieldDeclaration.getBegin().map(begin -> begin.line).orElse(0);
    }
    public RoleQuatreWrapper(SimpleName simpleName){
        this.nom = simpleName.asString();
        this.ligne = simpleName.getBegin().map(begin -> begin.line).orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleQuatreWrapper that = (RoleQuatreWrapper) o;
        return ligne == that.ligne && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, ligne);
    }

    @Override
    public String toString() {
        return "RoleTroisWrapper{" +
                "nom='" + nom + '\'' +
                ", ligne=" + ligne +
                '}';
    }
}
