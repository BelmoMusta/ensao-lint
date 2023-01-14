package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributeNameRule;
import com.ensao.gi5.lint.rules.AttributeVisibilityRule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeVisibilityTest {

    @Test
    public void testOnlyAttributeVisibilityActive() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeVisibilityRule());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testAttributeVisibilityKO() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeVisibilityRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(new File("testFiles/attributeVisibility/attributeVisibilityKO.java"));
        linter.run();
        assertEquals(4, linter.getAllViolations().size());
    }

    @Test void testAttributeVisibilityOK() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeVisibilityRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(new File("testFiles/attributeVisibility/attributeVisibilityOK.java"));
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

}
