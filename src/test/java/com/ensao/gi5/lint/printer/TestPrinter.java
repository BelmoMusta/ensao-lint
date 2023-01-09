package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestPrinter implements Printer {
    Map<String, List<Violation>> violations;

    public TestPrinter(Map<String, List<Violation>> violations) {
        this.violations = violations;
    }

    @Override
    public void printViolations(Collection<Violation> violations) {
        violations.forEach(v -> {
            this.violations.putIfAbsent(v.getRuleId(), new ArrayList<>());
            this.violations.get(v.getRuleId()).add(v);
        });
    }
}
