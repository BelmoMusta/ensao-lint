package com.ensao.gi5.lint.wrapper;

import java.util.List;

public class EnumWrapper {
    final private String name ;
    final private List<Element> enumElements ;

    public EnumWrapper(String name, List<Element> enumElements) {
        this.name = name;
        this.enumElements = enumElements;
    }

    public String getName() {
        return name;
    }

    public List<Element> getEnumElements() {
        return enumElements;
    }
}
