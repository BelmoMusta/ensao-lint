package com.ensao.gi5.rules;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ParseErrorRule;
import com.ensao.gi5.lint.rules.Rule;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseErrorTest {


    @Test
    public void testCompilationError() {
        Linter linter = new Linter();
        linter.registerRule(new ParseErrorRule());
        linter.registerSource(new File("testFiles/parseError/ParseErrorExample.java"));
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(1, linter.getSources().size());
        Rule parsErrorRule = linter.getRules().iterator().next();
        assertEquals(1, parsErrorRule.getViolations().size());
    }


}