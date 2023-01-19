package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AnonymousRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnonymousTest {
    private Linter linter;

    @Test
    public void testAnonymousRule() {

        linter = new Linter();
        linter.registerRule(new AnonymousRule());
        linter.registerSource("testFiles/anonymousInnerClass/AnonymousTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }
}
