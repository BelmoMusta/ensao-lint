package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.ClassAttributsNameRule;
import com.ensao.gi5.lint.rules.ClassConstantsNameRule;
import com.ensao.gi5.lint.rules.NamingRule;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassRulesTest {
    ClassAttributsNameRule rule1 = new ClassAttributsNameRule();
    ClassConstantsNameRule rule2 = new ClassConstantsNameRule();

    CompilationUnitWrapper compilationUnit;


    @Test
    public void testClassAttributsName() {
        File file = new File("testFiles/ClassEnumInterfaceName/ClassRulesTest.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule1.apply(compilationUnit);
        List<Violation> violation = rule1.getViolations().stream().collect(Collectors.toList());
        assertEquals(2, rule1.getViolations().size());
        assertEquals("ClassRulesTest.java", violation.get(0).getFileName());
        assertEquals(11, violation.get(0).getLine());
        assertEquals(12, violation.get(1).getLine());
    }

    @Test
    public void testClassConstantsName() {
        File file = new File("testFiles/ClassRules/ClassTestFile.java");
        compilationUnit = new CompilationUnitWrapper(file);
        rule2.apply(compilationUnit);
        List<Violation> violation = rule2.getViolations().stream().collect(Collectors.toList());
        assertEquals(1, rule2.getViolations().size());
        assertEquals("ClassRulesTest.java", violation.get(0).getFileName());
        assertEquals(20, violation.get(0).getLine());

    }
}
