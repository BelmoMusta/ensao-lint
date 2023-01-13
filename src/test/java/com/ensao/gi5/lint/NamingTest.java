package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamingTest {
    private Linter linter;
    @Test
    public void testClassNameRule() {

        linter = new Linter();
        linter.registerRule(new ClassNameRule());
        linter.registerSource("testFiles/className/ClassNameTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(3, linter.getAllViolations().size());

    }
    @Test
    public void testConstantsRule() {

        linter = new Linter();
        linter.registerRule(new ConstantsRule());
        linter.registerSource("testFiles/className/ClassNameTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
    @Test
    public void testEnumerationRule() {

        linter = new Linter();
        linter.registerRule(new EnumerationRule());
        linter.registerSource("testFiles/className/ClassNameTest.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(6, linter.getAllViolations().size());

    }
    @Test
    public void testLocalVariablesRule() {

        linter = new Linter();
        linter.registerRule(new LocalVariablesRule());
        linter.registerSource("testFiles/localVariables/LocalVariablesTest1.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
    @Test
    public void testAttributesRule() {

        linter = new Linter();
        linter.registerRule(new AttributesRule());
        linter.registerSource("testFiles/classAttributes/ClassAttributesNaming.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }
}
