package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.IfElseRule;
import com.ensao.gi5.lint.rules.NamingRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfElseViolationTest {
    IfElseRule rule = new IfElseRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testIfElseRule() {
        File file = new File("testFiles/IfElseStatement/IfElseTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule.getViolations().size());
        assertEquals("IfElseTestFile.java", violation.get(0).getFileName());
        assertEquals(14, violation.get(0).getLine());
    }
}
