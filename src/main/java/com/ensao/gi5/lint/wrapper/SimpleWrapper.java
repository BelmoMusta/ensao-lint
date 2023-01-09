package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.rules.Level;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Arrays;

public class SimpleWrapper {

    final private SimpleName simpleName;
    final private String rule;
    private String accessSpecifier;

    public SimpleWrapper(SimpleName simpleName, String rule) {
        this.simpleName = simpleName;
        this.rule = rule;

    }

    public SimpleWrapper addAccessSpecifier(String accessSpecifier){
        this.accessSpecifier = accessSpecifier;
        return this;
    }

    public SimpleName getSimpleName() {
        return simpleName;
    }

    public String getRule() {
        return rule;
    }


    public boolean isRuleOf(String... rulesId){
        return Arrays.asList(rulesId).contains(this.rule);
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }
}
