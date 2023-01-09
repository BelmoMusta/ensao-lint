package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class StatementRuleTest extends RuleTest {
    private static final String STATEMENT_TEST_FOLDER = "testFiles/Statement";


    @Test
    public void test_LINT_REG_006(){
        Map<String, List<Violation>> violations = getViolations(STATEMENT_TEST_FOLDER, new StatementRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_006).size(), 1);
    }

    @Test
    public void test_LINT_REG_015(){
        Map<String, List<Violation>> violations = getViolations(STATEMENT_TEST_FOLDER, new StatementRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_015).size(), 1);
    }

    @Test
    public void test_LINT_REG_018(){
        Map<String, List<Violation>> violations = getViolations(STATEMENT_TEST_FOLDER, new StatementRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_018).size(), 1);
    }

    @Test
    public void test_LINT_REG_009(){
        Map<String, List<Violation>> violations = getViolations(STATEMENT_TEST_FOLDER, new StatementRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_009).size(), 1);
    }

    @Test
    public void test_LINT_REG_010(){
        Map<String, List<Violation>> violations = getViolations(STATEMENT_TEST_FOLDER, new StatementRule());
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_010).size(), 1);
    }
}
