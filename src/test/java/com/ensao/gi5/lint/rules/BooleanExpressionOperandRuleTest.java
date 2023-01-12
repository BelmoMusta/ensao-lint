package com.ensao.gi5.lint.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;


import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

public class BooleanExpressionOperandRuleTest {
    private BooleanExpressionOperandRule boolExpRule = new BooleanExpressionOperandRule();
    private CompilationUnitWrapper compilationUnitWrapper;

    @Test
    public void testApply() {
        // Create a test compilation unit
        File file = new File("testFiles/normalExecution/BooleanExpressionTest.java");
        compilationUnitWrapper = new CompilationUnitWrapper(file);

        // Apply the rule
        boolExpRule.apply(compilationUnitWrapper);

        // Get the violations
        List<Violation> violations = boolExpRule.getViolations().stream().collect(Collectors.toList());

        // Assert that there are 2 violations
        assertEquals(2, violations.size());

        // Assert that the first violation has the correct description
        assertEquals("Boolean expression should not have more than 2 operands", violations.get(0).getDescription());

    }
}