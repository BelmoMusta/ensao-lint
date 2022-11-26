package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.LocalVariableNameRule;

class LocalVariableNameTest {
	
	private Linter linter; 

	@BeforeEach
	void setUp(){
		linter = new Linter(); 
	}

	@Test
	void testLocalVariableName() {
		linter.registerRule(new LocalVariableNameRule());
		linter.registerSource("testFiles/namingConvention/VariableName.java");
		linter.registerPrinter(new ConsolePrinter()); 
		linter.run(); 
		
		assertEquals(1, linter.getRules().size());
	}

}
