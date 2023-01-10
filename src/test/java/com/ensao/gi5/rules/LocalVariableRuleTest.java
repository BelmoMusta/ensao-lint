package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.LocalVariableNameRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class LocalVariableRuleTest {

	@Test
	public void testLocalVariableRule() throws InterruptedException {
        int numViolations=Utils.getRuleViolationsNumber(new LocalVariableNameRule(), new File(Paths.LOCAL_VARIABLE_FILE_PATH));
        assertEquals(2,numViolations);
	}
}
