package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.Level;

import java.util.ResourceBundle;

public class ViolationBuilder {
    private final Violation violation;
    final static ResourceBundle Descriptions =ResourceBundle.getBundle("Descriptions");



    public ViolationBuilder(){
        violation = new Violation();
    }

    public ViolationBuilder setDescription(String id){
        violation.setDescription(Descriptions.getString(id));
        return this ;
    }

    public ViolationBuilder setFileName(String fileName){
        violation.setFileName(fileName);
        return this ;
    }

    public ViolationBuilder setRuleId(String ruleId){
        violation.setRuleId(ruleId);
        return this ;
    }

    public ViolationBuilder setLine(int line){
        violation.setLine(line);
        return this ;
    }

    public ViolationBuilder setLevel(Level level){
        violation.setLevel(level);
        return this ;
    }
    public Violation build(){
        return new Violation(violation) ;
    }
}
