package com.ensao.gi5.lint.rules.violations;

public class ViolationMaker {
    public static Violation makeViolation(String name, String description ,int line){
        final Violation violation = new Violation();
        violation.setDescription(description);
        violation.setFileName(name);
        violation.setLine(line);
        return violation;
    }
}
