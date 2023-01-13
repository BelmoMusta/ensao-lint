package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.IfElseRule;
import com.ensao.gi5.lint.rules.LambdaRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfElseTest {
    private Linter linter;

    @Test
    public void testIfElseRule() {

        linter = new Linter();
        linter.registerRule(new IfElseRule());
        linter.registerSource("testFiles/missingBrackets/MissingBracketsTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
}
