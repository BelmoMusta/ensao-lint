package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.UnusedVariablesRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class UnusedVariablesRuleTest {

	@Test
	public void testUnusedVariablesRule() {
		int numViolations=Utils.getRuleViolationsNumber(new UnusedVariablesRule(), new File(Paths.UNUSED_VARIABLES_FILE_PATH));
		assertEquals(4, numViolations);
	}
}
