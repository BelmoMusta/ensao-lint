package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributesVisibilityRule;
import com.ensao.gi5.lint.rules.ClassNameRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisibilityTest {
    private Linter linter;
    @Test
    public void testAttributesVisibilityRule() {

        linter = new Linter();
        linter.registerRule(new AttributesVisibilityRule());
        linter.registerSource("testFiles/attributesVisibility/AttributesVisibilityTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(3, linter.getAllViolations().size());

    }
}
