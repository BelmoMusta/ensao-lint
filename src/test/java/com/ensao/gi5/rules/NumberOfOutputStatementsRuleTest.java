package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.NumberOfOutputStatementsRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class NumberOfOutputStatementsRuleTest {

	@Test
	public void testNumberOfOutputStatementsRule() throws InterruptedException {
        int numViolations=Utils.getRuleViolationsNumber(new NumberOfOutputStatementsRule(), new File(Paths.OUTPUT_STATEMENTS_FILE_PATH));
        assertEquals(2,numViolations);
	}
}
