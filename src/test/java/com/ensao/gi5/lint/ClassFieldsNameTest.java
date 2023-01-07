package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ClassFieldsNameRule;
import com.ensao.gi5.lint.rules.StaticFieldsNameRule;

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

	@Test
	void testClassStaticFieldName() {
		linter.registerRule(new StaticFieldsNameRule());
		linter.registerSource("testFiles/namingConvention/ClassStaticFieldName.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(1, linter.getRules().size());
		
	}
	
	

}
