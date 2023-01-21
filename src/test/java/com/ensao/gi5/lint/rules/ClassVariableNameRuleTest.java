package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ClassVariableNameRuleTest {

    @Test
    void apply() {
        Linter linter = new Linter();
        linter.registerRule(new ClassVariableNameRule());
        linter.registerSource(new File("testFiles/classVariableName/ValidName.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        ClassVariableNameRule classVariableNameRule = (ClassVariableNameRule) linter.getRules().iterator().next();
        assertEquals(0, classVariableNameRule.getViolations().size());

    }
}