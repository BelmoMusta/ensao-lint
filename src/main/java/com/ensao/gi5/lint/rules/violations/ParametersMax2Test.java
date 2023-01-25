package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ParameterMethodConstructorMax2Rule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametersMax2Test {
    ParameterMethodConstructorMax2Rule rule12 = new ParameterMethodConstructorMax2Rule();
    CompilationUnitWrapper compilationUnit;

    @Test
    public void testParametersMax2() {

        Linter linter = new Linter();
        linter.registerRule(new ParameterMethodConstructorMax2Rule());
        linter.registerSource("testFiles/normalExecution/ParamMax2Example.java");
        linter.registerPrinter(new ConsolePrinter());
        linter.run();
        File file = new File("testFiles/normalExecution/ParamMax2Example.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule12.apply(compilationUnit);
        List<Violation> violation = rule12.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule12.getViolations().size());
        assertEquals("ParamMax2Example.java", violation.get(0).getFileName());
        assertEquals(2, violation.get(0).getLine());
        assertEquals(5, violation.get(1).getLine());

    }
}
