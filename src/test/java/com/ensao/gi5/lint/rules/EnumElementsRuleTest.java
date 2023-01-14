package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumElementsRuleTest {
    private static final String TEST_FILE = "testFiles/enumExample.java";

    @Test
    public void testApply() {
        Linter linter = new Linter();

        linter.registerRule(new EnumElementRule());

        linter.registerSource(new File(TEST_FILE));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());

        Rule rule = linter.getRules().iterator().next();
        //assertEquals(3,linter.getAllViolations().size());
        assertEquals(3, rule.getViolations().size());

    }
}