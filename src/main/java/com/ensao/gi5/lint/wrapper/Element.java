package com.ensao.gi5.lint.wrapper;

public class Element {

    //Les attributs
    private final String name;
    private final int line;

    //Le constructeur générique
    public Element(String name, int line) {

        this.name = name;
        this.line = line;
    }

    //Les getteurs
    public String getName() { return this.name; }
    public int getLine() { return this.line; }
}
