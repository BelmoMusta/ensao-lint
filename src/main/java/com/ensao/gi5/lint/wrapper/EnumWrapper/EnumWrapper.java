package com.ensao.gi5.lint.wrapper.EnumWrapper;

import java.util.List;

public class EnumWrapper {
    final private String name;
    final private List<Element> elements;

    public EnumWrapper(String name, List<Element> elements) {
        this.name = name;
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public List<Element> getElements() {
        return elements;
    }
}
