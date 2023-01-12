package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AnnotClaIntEnumNamingRule;
import com.ensao.gi5.lint.rules.ClassFieldNamingRule;
import com.ensao.gi5.lint.rules.ConstantNamingRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamingTest {

    private Linter linter;

    @Test
    public void testConstantNamingRule() {

        linter = new Linter();

        linter.registerRule(new ConstantNamingRule());
        linter.registerSource("testFiles/namingTest/ConstantsTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());
    }

    @Test
    public void testClassNamingRule() {

        linter = new Linter();

        linter.registerRule(new AnnotClaIntEnumNamingRule());
        linter.registerSource("testFiles/namingTest/ClassNameTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

    @Test
    public void testInterfaceNamingRule() {

        linter = new Linter();

        linter.registerRule(new AnnotClaIntEnumNamingRule());
        linter.registerSource("testFiles/namingTest/InterfaceNameTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

    @Test
    public void testEnumNamingRule() {

        linter = new Linter();

        linter.registerRule(new AnnotClaIntEnumNamingRule());
        linter.registerSource("testFiles/namingTest/EnumNameTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

    @Test
    public void testAnnotationNamingRule() {

        linter = new Linter();

        linter.registerRule(new AnnotClaIntEnumNamingRule());
        linter.registerSource("testFiles/namingTest/AnnotationNameTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }


    /*@Test
    public void testClassFieldNamingRule() {

        linter = new Linter();

        linter.registerRule(new ClassFieldNamingRule());
        linter.registerSource("testFiles/namingTest/ClassFieldsTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());


    }*/




}
