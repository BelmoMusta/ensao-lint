package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.AttributeNameRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class AttributeNameRuleTest {

	@Test
	public void testAttributeNameRule() {
		int numViolations = Utils.getRuleViolationsNumber(new AttributeNameRule(), new File(Paths.ATTRIBUTE_NAME_FILE_PATH));
		assertEquals(2, numViolations);
	}
	
}
