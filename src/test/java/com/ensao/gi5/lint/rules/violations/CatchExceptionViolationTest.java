package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.CatchExceptionRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatchExceptionViolationTest {
    CatchExceptionRule rule = new CatchExceptionRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testCatchExceptionRule() {
        File file = new File("testFiles/CatchException/CatchExceptionTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals("Catch block don't have a logger", violation.get(0).getDescription());
        assertEquals(1, rule.getViolations().size());
        assertEquals("CatchExceptionTestFile.java", violation.get(0).getFileName());
        assertEquals(16, violation.get(0).getLine());
    }
}
