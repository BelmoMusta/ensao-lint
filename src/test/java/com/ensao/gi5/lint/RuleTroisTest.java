package com.ensao.gi5.lint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.rules.ruleTrois;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import org.junit.jupiter.api.Test;

public class RuleTroisTest {
	private ruleTrois localVariablesRule=new ruleTrois();
	private CompilationUnitWrapper compilationUnitWrapper;
	
	 @Test
	    public void testApply() {
	        // Create a test compilation unit
	        File file = new File("testFiles/normalExecution/LocalVariablesTest.java");
	        compilationUnitWrapper = new CompilationUnitWrapper(file);

	        // Apply the rule
	        localVariablesRule.apply(compilationUnitWrapper);

	        // Get the violations
	        List<Violation> violations = localVariablesRule.getViolations().stream().collect(Collectors.toList());

	        // Assert that there are 2 violations
	        assertEquals(1, violations.size());

	        // Assert that the first violation has the correct description
	        assertEquals("Local variable should start with a lowercase letter", violations.get(0).getDescription());

	        // Assert that the second violation has the correct file name
	        assertEquals("LocalVariablesTest.java", violations.get(0).getFileName());
	    }

	    @Test
	    public void testIsActive() {
	        assertTrue(localVariablesRule.isActive());
	    }
	
}
