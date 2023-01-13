package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import com.ensao.gi5.lint.rules.NamingRule;

public class NamingRuleTest {

    @Test
    public void testIsActive() {
        NamingRule rule = new NamingRule();
        assertTrue(rule.isActive());
    }


}

