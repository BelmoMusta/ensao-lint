package com.ensao.gi5.lint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.rules.EnumRule;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;
public class EnumRuleTest {
	 private EnumRule enumRule = new EnumRule();
	    private CompilationUnitWrapper compilationUnitWrapper;

	    @Test
	    public void testApply() {
	        // Create a test compilation unit
	        File file = new File("testFiles/normalExecution/EnumTest.java");
	        compilationUnitWrapper = new CompilationUnitWrapper(file);

	        // Apply the rule
	        enumRule.apply(compilationUnitWrapper);

	        // Get the violations
	        List<Violation> violations = enumRule.getViolations().stream().collect(Collectors.toList());

	        // Assert that there are 2 violations
	        assertEquals(2, violations.size());

	        // Assert that the first violation has the correct description
	        assertEquals("Enumeration elements must be in uppercase and use _ as separator", violations.get(0).getDescription());

	        // Assert that the second violation has the correct file name
	        assertEquals("testFiles/normalExecution/EnumTest.java", violations.get(1).getFileName());
	    }

	    @Test
	    public void testIsActive() {
	        assertTrue(enumRule.isActive());
	    }
}
