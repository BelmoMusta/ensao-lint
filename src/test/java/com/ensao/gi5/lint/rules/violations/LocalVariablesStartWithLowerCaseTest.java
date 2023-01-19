package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.LocalVariablesStartWithLowerCase;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class LocalVariablesStartWithLowerCaseTest {

    LocalVariablesStartWithLowerCase rule = new LocalVariablesStartWithLowerCase();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testApply_ClassAttributesStartWithLowerCase_AddsViolation() {
        // Arrange
        File file = new File("testFiles/normalExecution/LocalVariablesStartWithLowerCaseTest.java");
        compilationUnit = new CompilationUnitWrapper(file);

        // Act
        rule.apply(compilationUnit);

        // Assert
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule.getViolations().size());
        assertEquals("VariableName must start with lower case", violation.get(0).getDescription());
        assertEquals("LocalVariablesStartWithLowerCaseTest.java", violation.get(0).getFileName());
        assertEquals(3, violation.get(0).getLine());
    }


    @Test
    public void testIsActive_AlwaysReturnsTrue() {
        // Act & Assert
        assertTrue(rule.isActive());
    }

}
