package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.printer.StringPrinter;
import com.ensao.gi5.lint.rules.*;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LinterTest {

    @Test
    public void testEmptyLinter() {
        Linter linter = new Linter();
        linter.run();
        assertEquals(0, linter.getRules().size());
        assertEquals(0, linter.getPrinters().size());
        assertEquals(0, linter.getSources().size());
    }

    @Test
    public void testUnusedImports() {
        final Linter linter = new Linter();
        linter.registerRule(new UnusedImportsRule());
        StringWriter stringWriter = new StringWriter();
        linter.registerPrinter(new StringPrinter(stringWriter));
        linter.registerSource("testFiles/normalExecution");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(2, linter.getSources().size());
        assertFalse(stringWriter.toString().isEmpty()); // make sur something is written
        int violationCount = linter.getAllViolations().size();

        assertEquals(3, violationCount);

    }
    @Test
    public void testNominationRule() {
        final Linter linter = new Linter();
        linter.registerRule(new NominationRule());
        StringWriter stringWriter = new StringWriter();
        linter.registerPrinter(new StringPrinter(stringWriter));
        linter.registerSource("testFiles");
        linter.run();

        assertEquals(1, linter.getRules().size());
        assertEquals(1, linter.getPrinters().size());
        assertEquals(6, linter.getSources().size());
        assertFalse(stringWriter.toString().isEmpty()); // make sur something is written
        int violationCount = linter.getAllViolations().size();

        assertEquals(1, violationCount);

    }



    @Test
    public void testUnusedImportsWhenStaticCallsKO() {
        final Linter linter = new Linter();
        linter.registerRule(new UnusedImportsRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/staticCall/StaticCallExample.java");
        linter.run();

         int violationCount = linter.getAllViolations().size();
        assertEquals(2, violationCount);

    }
 @Test
    public void testUnusedImportsWhenStaticCallsOK() {
        final Linter linter = new Linter();
        linter.registerRule(new UnusedImportsRule());
        linter.registerSource("testFiles/staticCall/StaticCallExampleOK.java");
        linter.run();

         int violationCount = linter.getAllViolations().size();
        assertEquals(0, violationCount);

    }
    @Test
    public void testMemberRule() {
        final Linter linter = new Linter();
        linter.registerRule(new MembersRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/Test.java");
        linter.run();

        int violationCount = linter.getAllViolations().size();
        assertEquals(1, violationCount);

    }
    @Test
    public void testBodySizeRule() {
        final Linter linter = new Linter();
        linter.registerRule(new MethodbodyRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/Test.java");
        linter.run();

        int violationCount = linter.getAllViolations().size();
        assertEquals(1, violationCount);

    }
    @Test
    public void testLocalavarRule() {
        final Linter linter = new Linter();
        linter.registerRule(new LocalvarRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource("testFiles/Test.java");
        linter.run();

        int violationCount = linter.getAllViolations().size();
        assertEquals(3, violationCount);

    }

}