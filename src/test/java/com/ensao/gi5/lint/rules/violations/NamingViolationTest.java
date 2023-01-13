package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.NamingRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamingViolationTest {
    NamingRule rule = new NamingRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testNamingRule() {
        File file = new File("testFiles/ClassEnumInterfaceName/NamingTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(3, rule.getViolations().size());
        assertEquals("NamingTestFile.java", violation.get(0).getFileName());
        assertEquals(9, violation.get(0).getLine());
        assertEquals(13, violation.get(1).getLine());
        assertEquals(18, violation.get(2).getLine());
    }


}
