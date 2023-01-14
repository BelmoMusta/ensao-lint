package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.CsvPrinter;
import com.ensao.gi5.lint.printer.HtmlPrinter;
import com.ensao.gi5.lint.rules.ClauseIfEseRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClauseIfElseTest {
    ClauseIfEseRule rule = new ClauseIfEseRule();
    CompilationUnitWrapper compilationUnit;
    @Test
    public void testClauseIfElse() {
        Linter linter = new Linter();

        linter.registerRule(new ClauseIfEseRule());
        linter.registerSource("testFiles/normalExecution/IfElseExample.java");
        linter.registerPrinter(new HtmlPrinter());
        linter.run();

        File file = new File("testFiles/normalExecution/IfElseExample.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule.apply(compilationUnit);
        List<Violation> violation = rule.getViolations().stream().collect(Collectors.toList());
        //tests passed
        assertEquals(1, rule.getViolations().size());
        assertEquals("IfElseExample.java", violation.get(0).getFileName());
        assertEquals(4, violation.get(0).getLine());
    }
}
