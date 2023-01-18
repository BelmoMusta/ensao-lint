package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.TypeNameRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeNameTest {

    @Test
    public void testLinterHasOneActiveRule() {
        Linter linter = new Linter();
        linter.registerRule(new TypeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        assertEquals(1, linter.getRules().size());
    }

    @Test
    public void testTypeNameOK() {
        Linter linter = new Linter();
        linter.registerRule(new TypeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/typeName/typeNameOk.java");
        linter.run();
        assertEquals(0, linter.getAllViolations().size());
    }

    @Test
    public void testTypeNameKoUnderscore() {
        Linter linter = new Linter();
        linter.registerRule(new TypeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/typeName/typeNameKoUnderscore.java");
        linter.run();
        assertEquals(4, linter.getAllViolations().size());
    }

    @Test
    public void testTypeNameKoLowerCase() {
        Linter linter = new Linter();
        linter.registerRule(new TypeNameRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/typeName/typeNameKoLowerCase.java");
        linter.run();
        assertEquals(4, linter.getAllViolations().size());
    }

}
