package com.ensao.gi5.lint.wrapper;

import java.util.List;

public class EnumerationWrapper {

    //Les attributs
    private final String name;
    private final List<Element> elementList;

    //Le constructeur générique
    public EnumerationWrapper(String name, List<Element> elementList) {

        this.name = name;
        this.elementList = elementList;
    }

    //Les getteurs
    public String getName() { return this.name; }
    public List<Element> getElementList() { return this.elementList; }

}
