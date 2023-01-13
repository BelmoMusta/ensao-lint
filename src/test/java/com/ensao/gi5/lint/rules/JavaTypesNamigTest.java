package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaTypesNamigTest {
    private TypeJavaNaming rule = new TypeJavaNaming();

    @Test
    public void testRule() {
        File file = new File("testFiles/normalExecution/javaTypesNamigExample.java");
        CompilationUnitWrapper cu = new CompilationUnitWrapper(file);
        rule.apply(cu);

        // Get the violations
        List<Violation> violations = new ArrayList<>(rule.getViolations());

        // Assert that there is a violation
        assertEquals(1, violations.size());

        // Assert that the violation has the correct description
        assertEquals("Java types should start with an uppercase letter and should not have underscores" , violations.get(0).getDescription());
    }

    @Test
    public void testIsActive() {
        assertTrue(rule.isActive());
    }
}
