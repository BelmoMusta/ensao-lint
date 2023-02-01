package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.IfElseStatementsRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class IfElseStatementsRuleTest {

	@Test
	public void testIfElseStatementsRule() {
		int numViolations=Utils.getRuleViolationsNumber(new IfElseStatementsRule(),new File(Paths.IF_ELSE_FILE_PATH));
		assertEquals(1, numViolations);
	}
}
