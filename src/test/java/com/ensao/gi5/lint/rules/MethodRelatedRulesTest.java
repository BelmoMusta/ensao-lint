package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodRelatedRulesTest {

    private static final String TEST_FILE = "testFiles/AbtMethod.java";

    @Test
    public void testApply(){
        Linter linter = new Linter();

        linter.registerRule(new LocalVariableNamingRule());
        linter.registerRule(new MethodBodyRule());
        linter.registerRule(new MethodsInTotalRule());
        linter.registerRule(new MethodParemetersRule());

        linter.registerSource(new File(TEST_FILE));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(4, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        Rule methodRules = linter.getRules().iterator().next();

        assertEquals(5, linter.getAllViolations().size());

    }
}
