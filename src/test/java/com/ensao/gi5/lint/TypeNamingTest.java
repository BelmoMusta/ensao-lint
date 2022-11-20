package com.ensao.gi5.lint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.TypeNamingRule;

class TypeNamingTest {
	
	private Linter linter; 
	
	@BeforeEach
	void setUp(){
		linter = new Linter(); 
	}

	@Test
	void testClassNames() {
		linter.registerPrinter(new ConsolePrinter());
		linter.registerRule(new TypeNamingRule());
		linter.registerSource("testFiles/namingConvention/classNames.java");
		
		linter.run();
		
		int violationCount = linter.getAllViolations().size(); 
		assertEquals(1, violationCount); 
		
	}
	
	@Test
	void testInterfaceNames() {
		linter.registerPrinter(new ConsolePrinter());
		linter.registerRule(new TypeNamingRule());
		linter.registerSource("testFiles/namingConvention/interfaceNames.java");
		
		linter.run();
		
		int violationCount = linter.getAllViolations().size(); 
		assertEquals(1, violationCount); 
		
	}
	
	@Test
	void testEnumNames() {
		linter.registerPrinter(new ConsolePrinter());
		linter.registerRule(new TypeNamingRule());
		linter.registerSource("testFiles/namingConvention/Enum_Names.java");
		
		linter.run();
		
		int violationCount = linter.getAllViolations().size(); 
		assertEquals(1, violationCount); 
		
	}
	@Test
	void testAnnotationNames() {
		linter.registerPrinter(new ConsolePrinter());
		linter.registerRule(new TypeNamingRule());
		linter.registerSource("testFiles/namingConvention/Annotation_Names.java");
		
		linter.run();
		
		int violationCount = linter.getAllViolations().size(); 
		assertEquals(1, violationCount); 
		
	}
	

}
