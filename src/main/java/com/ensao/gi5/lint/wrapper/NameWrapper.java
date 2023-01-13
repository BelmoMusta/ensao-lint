package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

public class NameWrapper {
    final private SimpleName simpleName ;
    final private String rule ;
    private String accessSpec ;


    public NameWrapper(SimpleName simpleName, String rule) {
        this.simpleName = simpleName;
        this.rule = rule;
    }

    public SimpleName getSimpleName() {
        return simpleName;
    }

    public String getRule() {
        return rule;
    }

    public String getAccessSpec() {
        return accessSpec;
    }

    public NameWrapper addAccessSpec(String accessSpec){
        this.accessSpec=accessSpec;
        return this ;
    }
}
