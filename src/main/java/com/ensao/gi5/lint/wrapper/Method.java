package com.ensao.gi5.lint.wrapper;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Method {


    private final String name;
    private final String returnType;
    private final Set<Parameter> parameters;
    private final int line;
    private int returnCount;
    private int throwCount;
    private int linesCounts;

    public Method(String name, String returnType, int line) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = new HashSet<>();
        this.line = line;
        this.linesCounts = 0;
    }

    public void setLinesCount(int lines) {
        this.linesCounts = lines;
    }

    //La redéfinition de la méthode toString()
    @Override
    public String toString() {
        return String.format("%s %s(%s)", this.returnType, this.name,
                String.join(", ", parameters.stream().map(Parameter::toString).toArray(String[]::new)));
    }

}
