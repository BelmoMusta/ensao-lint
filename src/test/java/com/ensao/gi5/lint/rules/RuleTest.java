package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.TestPrinter;
import com.ensao.gi5.lint.rules.violations.Violation;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RuleTest {



    @BeforeAll
    public static void parentInit(){

    }


    protected Map<String, List<Violation>> getViolations(String folder, Rule... rules){
        Map<String, List<Violation>> violations = new HashMap<>();
        Linter linter = new Linter();
        linter.registerPrinter(new TestPrinter(violations));
        for(Rule rule : rules) linter.registerRule(rule);
        linter.registerSource(folder);
        linter.run();
        return violations;
    }



}
