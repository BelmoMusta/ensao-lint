package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.Rule;
import com.ensao.gi5.lint.rules.violations.Violation;

import java.util.ArrayList;
import java.util.List;

public abstract class LinterRunner {

    protected void runLinter(String folderName , Rule rule){
        Linter linter = new Linter();
        linter.registerRule(rule);
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(folderName);
        linter.run();
    }

    protected List<Violation> getViolations(String folderName , Rule rule){
        List<Violation> violations = new ArrayList<>();
        runLinter(folderName,rule);
        return violations;
    }
}
