package com.ensao.gi5.lint.wrapper.enumeration;

import java.util.List;

public class EnumWrapper {
	final private String name;
    final private List<EnumElement> elements;

    public EnumWrapper(String name, List<EnumElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public List<EnumElement> getElements() {
        return elements;
    }
}
