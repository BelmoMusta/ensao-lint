package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.UnusedImportsRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class UnusedImportsTest {

	@Test
	public void testUnusedImportsRule() {
        int numViolations=Utils.getRuleViolationsNumber(new UnusedImportsRule(), new File(Paths.UNUSED_IMPORTS_FILE_PATH));
        assertEquals(2, numViolations);
	}
	
}
