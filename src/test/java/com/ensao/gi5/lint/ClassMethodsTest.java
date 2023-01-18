package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ClassMethodsRule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassMethodsTest {

    @Test
    public void testOnlyClassMethodsRuleIsActive() {
        Linter linter = new Linter();
        linter.registerRule(new ClassMethodsRule());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testClassMethodsRuleOK() {
        Linter linter = new Linter();
        linter.registerRule(new ClassMethodsRule());
        linter.registerSource(new File("testFiles/classMethods/classMethodsOK.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

    @Test
    public void testClassMethodsRuleKO() {
        Linter linter = new Linter();
        linter.registerRule(new ClassMethodsRule());
        linter.registerSource(new File("testFiles/classMethods/classMethodsKO.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getAllViolations().size());
    }

}
