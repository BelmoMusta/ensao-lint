package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.NumberOfMethodsRule;
import com.ensao.gi5.lint.rules.NumberOfParametersRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfParamsViolationTest {

    NumberOfParametersRule rule = new NumberOfParametersRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testNumberOfMethods() {
        File file = new File("testFiles/NumberOfParams/NumberOfParamsTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule.getViolations().size());
        assertEquals("NumberOfParamsTestFile.java", violation.get(0).getFileName());
        assertEquals(11, violation.get(0).getLine());
        assertEquals(37, violation.get(1).getLine());
    }
}
