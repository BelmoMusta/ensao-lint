package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class RoleDouzeWrapper {
    private  String nom;
    private  int ligne;

    private  int count;

    public RoleDouzeWrapper(String nom, int ligne){
        this.nom = nom;
        this.ligne = ligne;
    }
    public RoleDouzeWrapper(int count, int ligne){
        this.count = count;
        this.ligne = ligne;
    }


    public int getCount() {
        return count;
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
        RoleDouzeWrapper that = (RoleDouzeWrapper) o;
        return ligne == that.ligne && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, ligne);
    }
}
