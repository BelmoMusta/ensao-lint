package com.ensao.gi5.lint.wrapper.classe;

import java.util.ArrayList;
import java.util.List;

public class ClassWrapper {
	
	final private String name;
    final private List<Construct> constructs;
    final private List<Methode> methodes;
    final private int ligne;


    public ClassWrapper(String name, int ligne) {
        this.name = name;
        this.ligne = ligne;
        this.constructs = new ArrayList<>();
        this.methodes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLigne() {
        return ligne;
    }

    public List<Methode> getMethodes() {
        return methodes;
    }

    public List<Construct> getConstructs() {
        return constructs;
    }
}
