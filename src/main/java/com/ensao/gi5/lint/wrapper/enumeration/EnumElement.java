package com.ensao.gi5.lint.wrapper.enumeration;

public class EnumElement {
	final private String name;
    final private int ligne;

    public EnumElement(String name, int ligne) {
        this.name = name;
        this.ligne = ligne;
    }

    public String getName() {
        return name;
    }

    public int getLigne() {
        return ligne;
    }
}
