package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodsTest {
    private Linter linter;
    @Test
    public void testMethodLengthRule() {

        linter = new Linter();
        linter.registerRule(new MethodLengthRule());
        linter.registerSource("testFiles/methodLength/MethodLengthTest1.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }
    @Test
    public void testNumberOfMethodsByClassRule() {

        linter = new Linter();
        linter.registerRule(new NumberOfMethodsByClassRule());
        linter.registerSource("testFiles/numberOfMethodsByClass/NumberOfMethodsByClassTest1.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }
    @Test
    public void testNumberOfParametersRule() {

        linter = new Linter();
        linter.registerRule(new NumberOfParametersRule());
        linter.registerSource("testFiles/numberOfParameters/NumberOfParametersTest1.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
    @Test
    public void testReturnCountRule() {

        linter = new Linter();
        linter.registerRule(new ReturnCountRule());
        linter.registerSource("testFiles/returnCount/ReturnCountTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
}
