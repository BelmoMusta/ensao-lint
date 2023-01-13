package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.MethodAndConstructorParams;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodAndConstructorParamsTest {

    private MethodAndConstructorParams rule = new MethodAndConstructorParams();
    private CompilationUnitWrapper compilationUnit;

    @Test
    public void testApply_TooManyParametersInConstructor() {
        // Arrange
        File file = new File("testFiles/normalExecution/MethodAndConstructorParamsTest.java");
        compilationUnit = new CompilationUnitWrapper(file);
        // Act
        rule.apply(compilationUnit);

        // Assert
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals("Too many parameters in constructor", violation.get(0).getDescription());
        assertEquals("MethodAndConstructorParamsTest.java", violation.get(0).getFileName());
        assertEquals(1, violation.size());
    }


    @Test
    public void testIsActive() {
        assertTrue(rule.isActive());
    }
}
