package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClausesRuleTest {
    @Test
    public void testApply() {
        Linter linter = new Linter();
        linter.registerRule(new ClausesRule());
        linter.registerSource(new File("testFiles/clauses/ValidClauses.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        ClausesRule clausesRule = (ClausesRule) linter.getRules().iterator().next();
        assertEquals(0, clausesRule.getViolations().size());
    }

}