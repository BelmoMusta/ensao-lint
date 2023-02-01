package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.NumberOfMethodsRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class NumberOfMethodsRuleTest {

	@Test
	public void testMethodBodyRule() {
		int numViolations=Utils.getRuleViolationsNumber(new NumberOfMethodsRule(), new File(Paths.HIGHT_NUMBER_OF_METHODS_BODY_FILE_PATH));
		assertEquals(1, numViolations);
	}
}
