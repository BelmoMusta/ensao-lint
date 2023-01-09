package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class ClassRuleTest extends RuleTest{
    private static final String CLASS_TEST_FOLDER = "testFiles/Class";



    @Test
    public void test_LINT_REG_008(){
        Map<String, List<Violation>> violations = getViolations(CLASS_TEST_FOLDER, new ClassRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_008).size(), 1);
    }

    @Test
    public void test_LINT_REG_011(){
        Map<String, List<Violation>> violations = getViolations(CLASS_TEST_FOLDER, new ClassRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_011).size(), 1);
    }

    @Test
    public void test_LINT_REG_012(){
        Map<String, List<Violation>> violations = getViolations(CLASS_TEST_FOLDER, new ClassRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_012).size(), 1);
    }

    @Test
    public void test_LINT_REG_014(){
        Map<String, List<Violation>> violations = getViolations(CLASS_TEST_FOLDER, new ClassRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_014).size(), 2);
    }

    @Test
    public void test_LINT_REG_017(){
        Map<String, List<Violation>> violations = getViolations(CLASS_TEST_FOLDER, new ClassRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_017).size(), 2);
    }
}
