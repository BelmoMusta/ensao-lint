package com.ensao.gi5.lint.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

public class IfElseRuleTest {
    private IfElseRule ifElseRule = new IfElseRule();
    private CompilationUnitWrapper compilationUnitWrapper;

    @Test
    public void testApply() {
        // Create a test compilation unit
        File file = new File("testFiles/normalExecution/IfElseTest.java");
        compilationUnitWrapper = new CompilationUnitWrapper(file);

        // Apply the rule
        ifElseRule.apply(compilationUnitWrapper);

        // Get the violations
        List<Violation> violations = ifElseRule.getViolations().stream().collect(Collectors.toList());

        // Assert that there are 2 violations
        assertEquals(2, violations.size());

        // Assert that the first violation has the correct description
        assertEquals("If statement should have curly braces", violations.get(0).getDescription());

        // Assert that the second violation has the correct file name
        assertEquals("IfElseTest.java", violations.get(1).getFileName());
    }

    @Test
    public void testIsActive() {
        assertTrue(ifElseRule.isActive());
    }
}
