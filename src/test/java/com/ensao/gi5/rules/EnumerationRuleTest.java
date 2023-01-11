package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.EnumerationElementsRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class EnumerationRuleTest {

	@Test
	public void testEnumerationRule() {
		int numViolations=Utils.getRuleViolationsNumber(new EnumerationElementsRule(),new File(Paths.ENUMERATION_ELEMENTS_FILE_PATH));
		assertEquals(2, numViolations);
	}
}
