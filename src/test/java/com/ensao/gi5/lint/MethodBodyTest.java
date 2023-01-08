package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.MethodBodyRule;

class MethodBodyTest {

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



}
