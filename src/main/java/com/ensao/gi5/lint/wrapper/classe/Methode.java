package com.ensao.gi5.lint.wrapper.classe;

import java.util.ArrayList;
import java.util.List;

public class Methode {

    final private String name;
    final private String returnType;
    final private List<Parametre> parametres;
    final private String accessSpecifier;
    final private int ligne;


    public Methode(String name, String returnType, String accessSpecifier, int ligne) {
        this.name = name;
        this.returnType = returnType;
        this.accessSpecifier = accessSpecifier;
        this.parametres = new ArrayList<>();
        this.ligne = ligne;
    }

    public String getName() {
        return name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<Parametre> getParametres() {
        return parametres;
    }

    public int getLigne() {
        return ligne;
    }

    @Override
    public String toString() {
        return String.format("%s %s(%s)", this.returnType, this.name,
                String.join(", ", parametres.stream().map(Parametre::getType).toArray(String[]::new)));
    }
}