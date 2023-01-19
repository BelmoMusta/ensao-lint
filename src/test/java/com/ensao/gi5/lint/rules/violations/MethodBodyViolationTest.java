package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.MethodBodyRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodBodyViolationTest {
        MethodBodyRule rule = new MethodBodyRule();
        CompilationUnitWrapper compilationUnit;
        @Test
        public void testMethodBodyRue() {
            File file = new File("testFiles/MethodBody/MethodBodyTestFile.java");
            compilationUnit = new CompilationUnitWrapper(file);
            rule.apply(compilationUnit);
            List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
            assertEquals(1, rule.getViolations().size());
            assertEquals("MethodBodyTestFile.java", violation.get(0).getFileName());
            assertEquals(2, violation.get(0).getLine());
        }

}
