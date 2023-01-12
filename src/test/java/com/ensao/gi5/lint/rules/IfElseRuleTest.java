package com.ensao.gi5.lint.rules;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

public class IfElseRuleTest extends RuleTest{
    private static final String STATEMENT_TEST_FOLDER = "testFiles/Statement";

    private IfElseRule ifElseRule;
    
    @Test
    public void test_LINT_REG_018(){
    	Set<Violation> violations = getViolations(STATEMENT_TEST_FOLDER, ifElseRule);
        Assertions.assertEquals(violations.get(Constantes.LINT_REG_018).size(), 1);
    }
}
