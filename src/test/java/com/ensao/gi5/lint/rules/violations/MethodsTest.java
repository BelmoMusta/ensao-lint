package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ConstantNamingRule;
import com.ensao.gi5.lint.rules.MethodBodySizeRule;
import com.ensao.gi5.lint.rules.MethodsNumPerClassRule;
import com.ensao.gi5.lint.rules.ParamsNumPerMethodandConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodsTest {


    private Linter linter;

    @Test
    public void testMethodBodySizeRule() {

        linter = new Linter();

        linter.registerRule(new MethodBodySizeRule());
        linter.registerSource("testFiles/MethodsTest/MethodBodyTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

    @Test
    public void testMethodsNumPerClassRule() {

        linter = new Linter();

        linter.registerRule(new MethodsNumPerClassRule());
        linter.registerSource("testFiles/MethodsTest/MethodsPerClassTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getAllViolations().size());

    }

    @Test
    public void testParamsNumPerMethodandConstructor() {

        linter = new Linter();

        linter.registerRule(new ParamsNumPerMethodandConstructor());
        linter.registerSource("testFiles/MethodsTest/NumberOfParamsTest.java");
        linter.registerPrinter(new ConsolePrinter());

        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(2, linter.getAllViolations().size());

    }

}
