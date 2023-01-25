package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.CsvPrinter;
import com.ensao.gi5.lint.rules.LocalVariablesRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;



import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocalVariablesTest {
    LocalVariablesRule rule3 = new LocalVariablesRule();
    CompilationUnitWrapper compilationUnit;

    @Test
    public void testLocalVariables() {
        Linter linter = new Linter();

        linter.registerRule(new LocalVariablesRule());
        linter.registerSource("testFiles/normalExecution/LocalVariableExample.java");
        linter.registerPrinter(new CsvPrinter());
        linter.run();

        File file = new File("testFiles/normalExecution/LocalVariableExample.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule3.apply(compilationUnit);
        List<Violation> violation = rule3.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule3.getViolations().size());
        assertEquals("LocalVariableExample.java", violation.get(0).getFileName());
        assertEquals(3, violation.get(0).getLine());
        assertEquals(4, violation.get(1).getLine());
        //tests passed
    }
}
