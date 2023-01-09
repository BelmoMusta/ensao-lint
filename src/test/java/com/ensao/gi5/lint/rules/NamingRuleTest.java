package com.ensao.gi5.lint.rules;


import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class NamingRuleTest extends RuleTest {
    private static final String NAMING_TEST_FOLDER = "testFiles/Naming";



    @Test
    public void test_LINT_REG_002(){
        Map<String, List<Violation>> violations = getViolations(NAMING_TEST_FOLDER, new NamingRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_002).size(), 2);
        violations.get(Constantes.LINT_REG_002).forEach(v ->
                Assertions.assertTrue(Pattern.compile("itest|Naming_Test").matcher(v.getDescription()).find())
        );
    }

    @Test
    public void test_LINT_REG_003(){
        Map<String, List<Violation>> violations = getViolations(NAMING_TEST_FOLDER, new NamingRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_003).size(), 1);
        Assertions.assertTrue(violations.get(Constantes.LINT_REG_003).get(0).getDescription().contains("Test"));
    }

    @Test
    public void test_LINT_REG_004(){
        Map<String, List<Violation>> violations = getViolations(NAMING_TEST_FOLDER, new NamingRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_004).size(), 1);
        Assertions.assertTrue(violations.get(Constantes.LINT_REG_004).get(0).getDescription().contains("Toto"));
    }

    @Test
    public void test_LINT_REG_005(){
        Map<String, List<Violation>> violations = getViolations(NAMING_TEST_FOLDER, new NamingRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_005).size(), 1);
        Assertions.assertTrue(violations.get(Constantes.LINT_REG_005).get(0).getDescription().contains("constant"));
    }

    @Test
    public void test_LINT_REG_013(){
        Map<String, List<Violation>> violations = getViolations(NAMING_TEST_FOLDER, new NamingRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_013).size(), 1);
        Assertions.assertTrue(violations.get(Constantes.LINT_REG_013).get(0).getDescription().contains("noSpecifiedAccess"));
    }

}
