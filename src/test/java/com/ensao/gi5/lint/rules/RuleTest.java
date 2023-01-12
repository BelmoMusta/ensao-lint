package com.ensao.gi5.lint.rules;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.rules.violations.Violation;

public class RuleTest {

	protected Set<Violation> getViolations(String folder, Rule... rules){
		Set<Violation> violations = new TreeSet<>();
        Linter linter = new Linter();
        for(Rule rule : rules) linter.registerRule(rule);
        linter.registerSource(folder);
        linter.run();
        return violations;
    }

}
