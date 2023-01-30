package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RulesTest {

    @Test
    public void testRule002() {
        final Linter linter = new Linter();

        linter.registerRule(new TypeStartWIthMajWithoutUnderscore());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule002Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(1, linter.getAllViolations().size());
    }

    @Test
    public void testRule006() {
        final Linter linter = new Linter();

        linter.registerRule(new CheckExpression());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule006Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(2, linter.getAllViolations().size());
    }

    @Test
    public void testRule008() {
        final Linter linter = new Linter();

        linter.registerRule(new MethodBody());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule008Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(0, linter.getAllViolations().size());
    }

    @Test
    public void testRule011() {
        final Linter linter = new Linter();

        linter.registerRule(new MethodsNumbersLessThanTwinty());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule011Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(1, linter.getAllViolations().size());
    }

    @Test
    public void testRule012() {
        final Linter linter = new Linter();

        linter.registerRule(new NumberParametersRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule012Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(1, linter.getAllViolations().size());
    }

    @Test
    public void testRule013() {
        final Linter linter = new Linter();

        linter.registerRule(new ClearVisibilityOfClasses());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/testingRules/Rule013Test.java");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        assertEquals(2, linter.getAllViolations().size());
    }
}
