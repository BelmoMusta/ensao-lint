package com.ensao.gi5.lint.rules.violations;


import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributeVisibilityRule;
import com.ensao.gi5.lint.rules.MethodBodySizeRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldVisibilityTest {

    private Linter linter;

    @Test
    public void testMethodBodySizeRule() {

        linter = new Linter();

        linter.registerRule(new AttributeVisibilityRule());
        linter.registerSource("testFiles/VisibilityTest/FieldVisibilityTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

}
