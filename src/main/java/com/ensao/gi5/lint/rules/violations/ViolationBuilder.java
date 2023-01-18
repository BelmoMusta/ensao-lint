package com.ensao.gi5.lint.rules.violations;

public class ViolationBuilder {
    private final Violation violation;

    public ViolationBuilder() {
        violation = new Violation();
    }

    public ViolationBuilder withDescription(String description) {
        violation.setDescription(description);
        return this;
    }

    public ViolationBuilder withFileName(String fileName) {
        violation.setFileName(fileName);
        return this;
    }

    public ViolationBuilder withLine(int line) {
        violation.setLine(line);
        return this;
    }

    public Violation build() {
        return new Violation(violation);
    }
}
