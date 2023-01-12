package com.ensao.gi5.lint.rules;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

public class ClassRuleTest extends RuleTest {
    private static final String CLASS_TEST_FOLDER = "testFiles/Class";

    private ClassRule classRule;

    @Test
    public void test_LINT_REG_008(){
        Set<Violation> violations = getViolations(CLASS_TEST_FOLDER, classRule);
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_008).size(), 1);
    }

    @Test
    public void test_LINT_REG_011(){
    	Set<Violation> violations = getViolations(CLASS_TEST_FOLDER, classRule);
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_011).size(), 1);
    }

    @Test
    public void test_LINT_REG_012(){
    	Set<Violation> violations = getViolations(CLASS_TEST_FOLDER, classRule);
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_012).size(), 1);
    }
}
