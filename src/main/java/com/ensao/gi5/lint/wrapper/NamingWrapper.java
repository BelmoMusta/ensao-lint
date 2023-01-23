package com.ensao.gi5.lint.wrapper;

import java.util.Arrays;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.SimpleName;

public class NamingWrapper {
    private final String name;
    private final int line;

    public NamingWrapper(SimpleName name, int line) {
        this.name = String.valueOf(name);
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }


    public boolean startsWithUpperCase(String typeName) {
        return Character.isUpperCase(typeName.charAt(0));
    }

    public boolean containsUnderscore(String typeName) {
        return typeName.contains("_");
    }

}