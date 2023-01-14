package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.BooleanExpressionRule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanExpressionTest {

    @Test
    public void testOnlyBooleanExpressionRuleIsActive() {
        Linter linter = new Linter();
        linter.registerRule(new BooleanExpressionRule());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testBooleanExpressionRuleKO() {
        Linter linter = new Linter();
        linter.registerRule(new BooleanExpressionRule());
        linter.registerSource(new File("testFiles/booleanExpressions/booleanExpressionsKO.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(4, linter.getAllViolations().size());
    }

    @Test
    public void testBooleanExpressionRuleOK() {
        Linter linter = new Linter();
        linter.registerRule(new BooleanExpressionRule());
        linter.registerSource(new File("testFiles/booleanExpressions/booleanExpressionsOK.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

}
