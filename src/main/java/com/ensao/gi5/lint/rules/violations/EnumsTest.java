package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.EnumsUpperCaseUnderscoreSeparatorRull;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumsTest {
    EnumsUpperCaseUnderscoreSeparatorRull rule7 = new EnumsUpperCaseUnderscoreSeparatorRull();
    CompilationUnitWrapper compilationUnit;

    @Test
    public void testEnums() {

        File file = new File("testFiles/normalExecution/EnumsExample.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule7.apply(compilationUnit);
        List<Violation> violation = rule7.getViolations().stream().collect(Collectors.toList());
        assertEquals(3, rule7.getViolations().size());
        assertEquals("EnumsExample.java", violation.get(0).getFileName());
        assertEquals(2, violation.get(0).getLine());

    }
}
