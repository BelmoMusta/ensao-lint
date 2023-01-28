package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.ExeptionHandlingRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class ExeptionHandlingRuleTest {

	@Test
	public void testExeptionHandlingRule() {
		int numViolations=Utils.getRuleViolationsNumber(new ExeptionHandlingRule(),new File(Paths.EXEPTION_HANDLING_FILE_PATH));
		assertEquals(1, numViolations);
	}
}
