package com.ensao.gi5.lint.wrapper;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;
public class ruleDeuzeWrapper {
	private  String nom;
    private  int ligne;

    private  int count;

    public ruleDeuzeWrapper(String nom, int ligne){
        this.nom = nom;
        this.ligne = ligne;
    }
    public ruleDeuzeWrapper(int count, int ligne){
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
        ruleDeuzeWrapper that = (ruleDeuzeWrapper) o;
        return ligne == that.ligne && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, ligne);
    }
}
