package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

import java.util.Arrays;

public class SimpleWrapper {

    //Les attributs
    private final SimpleName simpleName;
    private final String rule;
    private String access;

    //Le constructeur générique
    public SimpleWrapper(SimpleName simpleName, String rule) {

        this.simpleName = simpleName;
        this.rule = rule;
    }

    public SimpleWrapper setAccess(String access) {

        this.access = access;
        return this;
    }

    public String getRule() { return this.rule; }

    public SimpleName getSimpleName() { return this.simpleName; }

    public String getAccess() { return this.access; }

    public boolean isRuleOf(String... rules) {

        return Arrays.asList(rules).contains(this.rule);
    }
}
