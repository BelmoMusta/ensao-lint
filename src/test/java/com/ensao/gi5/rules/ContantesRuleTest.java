package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.ConstantesRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class ContantesRuleTest {

	@Test
	public void testConstantesRule() {
        int numViolations=Utils.getRuleViolationsNumber(new ConstantesRule(), new File(Paths.CONSTANTES_FILE_PATH));
        assertEquals(2, numViolations);
	}
}
