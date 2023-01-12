package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.MethodBodyRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class MethodBodyRuleTest {

	@Test
	public void testMethodBodyRule() {
		int numViolations=Utils.getRuleViolationsNumber(new MethodBodyRule(), new File(Paths.METHOD_BODY_FILE_PATH));
		assertEquals(1, numViolations);
	}
}
