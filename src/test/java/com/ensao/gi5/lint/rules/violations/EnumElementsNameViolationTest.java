package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.ClassAttributsNameRule;
import com.ensao.gi5.lint.rules.ClassConstantsNameRule;
import com.ensao.gi5.lint.rules.EnumElementsRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumElementsNameViolationTest {
    EnumElementsRule rule = new EnumElementsRule();

    CompilationUnitWrapper compilationUnit;
    @Test
    public void testClassConstantsName() {
        File file = new File("testFiles/EnumElementsName/EnumElementNameTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule.getViolations().size());
        assertEquals("EnumElementNameTestFile.java", violation.get(0).getFileName());
        assertEquals(11, violation.get(0).getLine());
        assertEquals(12, violation.get(1).getLine());

    }
}
