package com.ensao.gi5.lint.rules.violations;

public class ViolationFactory  {
    public static Violation ViolationFactory(String fileName,String description,int line){
        final Violation violation = new Violation();
        violation.setFileName(fileName);
        violation.setDescription(description);
        violation.setLine(line);
        return violation;
    }
}
