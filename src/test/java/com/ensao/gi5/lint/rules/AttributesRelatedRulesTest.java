package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributesRelatedRulesTest {

    private static final String TEST_FILE = "testFiles/AttributeExample.java";

    @Test
    public void testApply(){
        Linter linter = new Linter();

        linter.registerRule(new AttributesNamingRule());
        linter.registerRule(new AttributesVisibilityRule());

        linter.registerSource(new File(TEST_FILE));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(2, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());

        Rule attributeRules = linter.getRules().iterator().next();

        assertEquals(2, linter.getAllViolations().size());
    }

}
