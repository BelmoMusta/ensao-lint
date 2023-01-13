package com.ensao.gi5.lint.rules.violations;

public class ViolationFactory  {

    /**
     * I will implement desing pattern of Factory method
     * to create violation and that to make it easy to change
     * or extend the way they are created in the future without
     * affecting the rest of the codebase
     * **/
    public static Violation ViolationFactory(String fileName,String description,int line){
        final Violation violation = new Violation();
        violation.setFileName(fileName);
        violation.setDescription(description);
        violation.setLine(line);
        return violation;
    }
}
