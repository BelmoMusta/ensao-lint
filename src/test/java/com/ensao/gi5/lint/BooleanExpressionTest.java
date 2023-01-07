package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.BooleanExpressionRule;

class BooleanExpressionTest {
	
	private Linter linter;

	@BeforeEach
	void setUp() throws Exception {
		linter = new Linter(); 
	}

	@Test
	void testBooleanExpr() {
		linter.registerRule(new BooleanExpressionRule());
		linter.registerSource("testFiles/booleanExpr/OperandsCondition.java");
		linter.registerPrinter(new ConsolePrinter());
		
		linter.run(); 
		
		assertEquals(1, linter.getRules().size()); 
		assertEquals(6, linter.getAllViolations().size());
	}

}
