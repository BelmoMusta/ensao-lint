package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ClassNameRule;
import com.ensao.gi5.lint.rules.UnusedPrivateMethodsRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnusedPrivateMethodsTest {
    private Linter linter;
    @Test
    public void testUnusedPrivateMethodsRule() {

        linter = new Linter();
        linter.registerRule(new UnusedPrivateMethodsRule());
        linter.registerSource("testFiles/className/ClassNameTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }
}
