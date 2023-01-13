package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.EnumUpperRule;
import com.ensao.gi5.lint.runner.LinterRunner;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EnumUpperRuleTest extends LinterRunner {


    public static final String FILE_NAME_JAVA = "D:\\Gi5\\Java\\Projet\\ensao-lint\\testFiles\\nameRules\\NameRuleTest.java";

    @Test
    public void rule_007_Test(){
        List<Violation> violations = getViolations(FILE_NAME_JAVA,new EnumUpperRule());
        System.out.println(violations.get(0).getRuleId());
    }



}
