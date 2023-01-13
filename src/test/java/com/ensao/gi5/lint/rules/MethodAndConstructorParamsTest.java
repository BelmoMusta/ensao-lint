package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodAndConstructorParamsTest {
    private MethodAndConstructorParams rule = new MethodAndConstructorParams();

    @Test
    public void testApply() {
        File file = new File("testFiles/normalExecution/MethodAndConstructorParamsExample.java");
        CompilationUnitWrapper cu = new CompilationUnitWrapper(file);
        rule.apply(cu);

        // Get the violations
        List<Violation> violations = new ArrayList<>(rule.getViolations());

        // Assert that there is a violation
        assertEquals(2, violations.size());

        // Assert that the violation has the correct description
        assertEquals("Too many parameters in constructor" , violations.get(0).getDescription());
        assertEquals("Too many parameters in method" , violations.get(1).getDescription());
    }

    @Test
    public void testIsActive() {
        assertTrue(rule.isActive());
    }
}
