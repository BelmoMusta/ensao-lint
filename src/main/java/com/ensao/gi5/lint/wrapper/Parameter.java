package com.ensao.gi5.lint.wrapper;

public class Parameter {

    //Les attributs
    final private String parameterType;
    final private String parameterName;

    //Le constructeur générique
    public Parameter(String parameterType, String parameterName) {
        this.parameterType = parameterType;
        this.parameterName = parameterName;
    }

    public String getParameterType() {
        return this.parameterType;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    //La redéfinition de la méthode toString()
    @Override
    public String toString() {
        return this.parameterType + " " + this.parameterName;
    }
}
