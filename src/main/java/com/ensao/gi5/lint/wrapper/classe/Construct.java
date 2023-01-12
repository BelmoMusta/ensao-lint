package com.ensao.gi5.lint.wrapper.classe;

import java.util.ArrayList;
import java.util.List;

public class Construct {
	
    final private String name;
	final private List<Parametre> parametres;
    final private int ligne;

    public Construct(String name, int ligne) {
        this.name = name;
        this.parametres = new ArrayList<>();
        this.ligne = ligne;
    }
    
    public String getName() {
        return name;
    }

    public List<Parametre> getParametres() {
        return parametres;
    }

    public int getLigne() {
        return ligne;
    }

}
