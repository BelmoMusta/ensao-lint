package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.MethodStatementsRule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodLinesTest {

    @Test
    public void testOnlyMethodLinesRuleIsActive() {
        Linter linter = new Linter();
        linter.registerRule(new MethodStatementsRule());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testMethodLinesRuleOK() {
        Linter linter = new Linter();
        linter.registerRule(new MethodStatementsRule());
        linter.registerSource(new File("testFiles/methodLines/methodLinesOK.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

    @Test
    public void testMethodLinesRuleKO() {
        Linter linter = new Linter();
        linter.registerRule(new MethodStatementsRule());
        linter.registerSource(new File("testFiles/methodLines/methodLinesKO.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(2, linter.getAllViolations().size());
    }

}
