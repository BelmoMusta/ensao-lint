package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodParametersRuleTest {
    @Test
    public void testMethodWithTwoOrLessParameters() {
        Linter linter = new Linter();
        linter.registerRule(new MethodParametersRule());
        linter.registerSource(new File("testFiles/methodParameters/ValidMethodParameters.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        MethodParametersRule methodParametersRule = (MethodParametersRule) linter.getRules().iterator().next();
        assertEquals(0, methodParametersRule.getViolations().size());
    }

}