package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.AttributesVisibilityRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class AttributesVisibilityRuleTest {

	@Test
	public void testAttributesVisibilityRule() {
		int numViolations=Utils.getRuleViolationsNumber(new AttributesVisibilityRule(), new File(Paths.ATTRIBUTES_VISIBILITY_FILE_PATH));
		assertEquals(2, numViolations);
	}
}
