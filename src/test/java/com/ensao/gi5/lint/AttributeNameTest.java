package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributeNameRule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeNameTest {

    @Test
    public void testOnlyAttributeNameIsActive() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeNameRule());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testAttributeNameKO() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(new File("testFiles/attributeName/attributeNameKO.java"));
        linter.run();
        assertEquals(1, linter.getAllViolations().size());
    }

    @Test
    public void testAttributeNameOK() {
        Linter linter = new Linter();
        linter.registerRule(new AttributeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(new File("testFiles/attributeName/attributeNameOK.java"));
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

}
