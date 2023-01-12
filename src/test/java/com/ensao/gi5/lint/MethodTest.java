package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.LambdaExpressionRule;
import com.ensao.gi5.lint.rules.MethodBodyRule;
import com.ensao.gi5.lint.rules.NumberOfMethodRule;

class MethodTest {

	Linter linter;

	@BeforeEach
	void setUp() throws Exception {
		linter = new Linter();
	}

	@Test
	void testMethodBodyLength() {
		linter.registerRule(new MethodBodyRule());
		linter.registerSource("testFiles/methodRule/MethodBody.java");
		linter.registerPrinter(new ConsolePrinter());

		linter.run();
		
		assertEquals(1, linter.getAllViolations().size());

	}
	
	@Test
	void testNumberOfMethodPerClass() {
		linter.registerRule(new NumberOfMethodRule());
		linter.registerSource("testFiles/methodRule/Student.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(1, linter.getAllViolations().size());
	}
	
	@Test
	void testLambdaExpression() {
		linter.registerRule(new LambdaExpressionRule());
		linter.registerSource("testFiles/methodRule/LambdaExpression.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(1, linter.getAllViolations().size());
	}



}
