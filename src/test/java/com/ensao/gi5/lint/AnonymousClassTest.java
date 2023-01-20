package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AnonymousClassRule;

class AnonymousClassTest {

	private Linter linter;

	@BeforeEach
	void setUp() throws Exception {
		linter = new Linter(); 
	}

	@Test
	void testInstantiateAnonymousClass() {
		linter.registerRule(new AnonymousClassRule());
		linter.registerSource("testFiles/anonymousClassRule/AnonymousClass.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(2, linter.getAllViolations().size()); 
		
	}

}
