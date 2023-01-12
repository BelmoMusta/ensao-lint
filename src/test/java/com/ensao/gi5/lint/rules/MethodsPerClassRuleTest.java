package com.ensao.gi5.lint.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

public class MethodsPerClassRuleTest {
    private MethodsPerClassRule methodsPerClassRule = new MethodsPerClassRule();
    private CompilationUnitWrapper compilationUnitWrapper;

    @Test
    public void testApply() {
        // Create a test compilation unit
        File file = new File("testFiles/normalExecution/MethodsPerClassTest.java");
        compilationUnitWrapper = new CompilationUnitWrapper(file);

        // Apply the rule
        methodsPerClassRule.apply(compilationUnitWrapper);

        // Get the violations
        List<Violation> violations = methodsPerClassRule.getViolations().stream().collect(Collectors.toList());

        // Assert that there is 1 violation
        assertEquals(1, violations.size());

        // Assert that the violation has the correct description
        assertEquals("Class should not have more than 20 methods", violations.get(0).getDescription());

        // Assert that the violation has the correct file name
        assertEquals("MethodsPerClassTest.java", violations.get(0).getFileName());
    }

    @Test
    public void testIsActive() {
        assertFalse(methodsPerClassRule.isActive());
    }
}
