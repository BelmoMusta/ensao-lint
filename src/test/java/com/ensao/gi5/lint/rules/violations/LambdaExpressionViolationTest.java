package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.IfElseRule;
import com.ensao.gi5.lint.rules.LambdaExpressionRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaExpressionViolationTest {
    LambdaExpressionRule rule = new LambdaExpressionRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testLambdaExpression() {
        File file = new File("testFiles/LambdaExpression/LambdaExpressionTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule.getViolations().size());
        assertEquals("LambdaExpressionTestFile.java", violation.get(0).getFileName());
        assertEquals(12, violation.get(0).getLine());
    }
}
