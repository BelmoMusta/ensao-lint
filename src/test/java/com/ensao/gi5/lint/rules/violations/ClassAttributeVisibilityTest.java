package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.ClassAttributeVisibility;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassAttributeVisibilityTest {

    ClassAttributeVisibility rule = new ClassAttributeVisibility();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testApply_AttributeWithoutVisibility_AddsViolation() {
        // Arrange
        File file = new File("testFiles/normalExecution/ClassAttributeVisibilityTest.java");
        compilationUnit = new CompilationUnitWrapper(file);

        // Act
        rule.apply(compilationUnit);

        // Assert
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule.getViolations().size());
        assertEquals("Class attribute must have visibility declared", violation.get(0).getDescription());
        assertEquals("ClassAttributeVisibilityTest.java", violation.get(0).getFileName());
        assertEquals(2, violation.get(0).getLine());
    }


    @Test
    public void testIsActive_AlwaysReturnsTrue() {
        // Act & Assert
        assertTrue(rule.isActive());
    }
}
