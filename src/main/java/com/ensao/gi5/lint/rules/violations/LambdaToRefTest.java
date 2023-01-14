package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.LambdaReferenceMethodRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaToRefTest {

    LambdaReferenceMethodRule rule10 = new LambdaReferenceMethodRule();
    CompilationUnitWrapper compilationUnit;

    @Test
    public void testLambdaToRef() {
        Linter linter = new Linter();
        linter.registerRule(new LambdaReferenceMethodRule());
        linter.registerSource("testFiles/normalExecution/LambdaToRefExample.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        File file = new File("testFiles/normalExecution/LambdaToRefExample.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule10.apply(compilationUnit);
        List<Violation> violation = rule10.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule10.getViolations().size());
        assertEquals("LambdaToRefExample.java", violation.get(0).getFileName());
        assertEquals(3, violation.get(0).getLine());
        assertEquals(8, violation.get(1).getLine());
    }
}
