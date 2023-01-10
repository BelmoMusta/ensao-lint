package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.TypeNameRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class TypeNameRuleTest {

	@Test
	public void testTypeNameRule() {
        int numViolations=Utils.getRuleViolationsNumber(new TypeNameRule(), new File(Paths.TYPE_NAME_FILE_PATH));
        assertEquals(1, numViolations);
	}
}
