package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AnonymousRule;
import com.ensao.gi5.lint.rules.LambdaRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaInituitiveTest {
    private Linter linter;

    @Test
    public void testLambdaRule() {

        linter = new Linter();
        linter.registerRule(new LambdaRule());
        linter.registerSource("testFiles/lambdaInituitive/LambdaTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }
}
