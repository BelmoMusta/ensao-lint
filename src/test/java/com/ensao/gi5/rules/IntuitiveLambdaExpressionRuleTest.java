package com.ensao.gi5.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.rules.IntuitiveLambdaExpressionRule;
import com.ensao.gi5.test.constantes.Paths;
import com.ensao.gi5.test.utils.Utils;

public class IntuitiveLambdaExpressionRuleTest {

	@Test
	public void testIntuitiveLambdaExressionRule() {
		int numViolations=Utils.getRuleViolationsNumber(new IntuitiveLambdaExpressionRule(),new File(Paths.INTUITIVE_LAMBDA_EXPRESSION_FILE_PATH));
		assertEquals(1, numViolations);
	}
}
