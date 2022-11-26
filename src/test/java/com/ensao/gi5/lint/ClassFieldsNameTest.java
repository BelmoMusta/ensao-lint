package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ClassFieldsNameRule;

class ClassFieldsNameTest {
	
	private Linter linter;  

	@BeforeEach
	void setUp() {
		linter = new Linter();
	}

	@Test
	void testClassFieldName() {
		linter.registerRule(new ClassFieldsNameRule());
		linter.registerSource("testFiles/namingConvention/ClassFieldsName.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(1, linter.getRules().size());
		
	}

}
