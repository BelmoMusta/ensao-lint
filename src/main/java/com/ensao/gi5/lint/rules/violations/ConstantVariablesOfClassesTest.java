package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.HtmlPrinter;
import com.ensao.gi5.lint.printer.MarkdownPrinter;
import com.ensao.gi5.lint.rules.ConstantVariablesOfClassesRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantVariablesOfClassesTest {

    ConstantVariablesOfClassesRule rule5 = new ConstantVariablesOfClassesRule();
    CompilationUnitWrapper compilationUnit;

    @Test
    public void testConstantVariables() {

        File file = new File("testFiles/normalExecution/ConstantVariableExample.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule5.apply(compilationUnit);
        List<Violation> violation = rule5.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule5.getViolations().size());
        assertEquals("ConstantVariableExample.java", violation.get(0).getFileName());
        assertEquals(2, violation.get(0).getLine());

    }
}
