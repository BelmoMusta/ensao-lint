package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.ClassFieldsNameRule;
import com.ensao.gi5.lint.rules.ConstantNameRule;
import com.ensao.gi5.lint.rules.EnumFieldsNameRule;

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
		assertEquals(3, linter.getAllViolations().size());
	}

	@Test
	void testClassConstantName() {
		linter.registerRule(new ConstantNameRule());
		linter.registerSource("testFiles/namingConvention/ClassConstantName.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run();
		
		assertEquals(1, linter.getRules().size());
		assertEquals(1, linter.getAllViolations().size());
		
	}
	
	@Test
	void testEnumConstantName() {
		linter.registerRule(new EnumFieldsNameRule());
		linter.registerSource("testFiles/namingConvention/Enum_Names.java");
		linter.registerPrinter(new ConsolePrinter()); 
		
		linter.run(); 
		
		assertEquals(1, linter.getRules().size());
		assertEquals(2, linter.getAllViolations().size());
	}
	
	

}
