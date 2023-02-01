package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.NumberOfParametersRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class NumberOfParametersRuleTest {

	@Test
	public void testNumberOfParametersRule() {
		int numViolations = Utils.getRuleViolationsNumber(new NumberOfParametersRule(),
				new File(Paths.HIGHT_NUMBER_OF_PARAMETERS_FILE_PATH));
		assertEquals(2, numViolations);
	}
}
