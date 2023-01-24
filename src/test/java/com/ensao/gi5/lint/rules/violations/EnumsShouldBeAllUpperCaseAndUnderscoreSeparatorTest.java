package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.EnumsShouldBeAllUpperCaseAndUnderscoreSeparator;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class EnumsShouldBeAllUpperCaseAndUnderscoreSeparatorTest {
    EnumsShouldBeAllUpperCaseAndUnderscoreSeparator rule = new EnumsShouldBeAllUpperCaseAndUnderscoreSeparator();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testApply_EnumsShouldBeAllUpperCaseAndUnderscoreSeparator_AddsViolation() {
        // Arrange
        File file = new File("testFiles/normalExecution/EnumsShouldBeAllUpperCaseAndUnderscoreSeparatorTest.java");
        compilationUnit = new CompilationUnitWrapper(file);

        // Act
        rule.apply(compilationUnit);

        // Assert
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule.getViolations().size());
        assertEquals("color must have all characters to uppercase while using underscore separator", violation.get(0).getDescription());
        assertEquals("EnumsShouldBeAllUpperCaseAndUnderscoreSeparatorTest.java", violation.get(0).getFileName());
        assertEquals(1, violation.get(0).getLine());
    }


    @Test
    public void testIsActive_AlwaysReturnsTrue() {
        // Act & Assert
        assertTrue(rule.isActive());
    }
}
