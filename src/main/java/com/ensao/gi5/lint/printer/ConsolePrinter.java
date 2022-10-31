package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.util.Collection;

public class ConsolePrinter implements Printer {
	@Override
	public void printViolations(Collection<Violation> violations) {
		for (Violation violation : violations) {
			System.out.println(violation);
		}
		
	}
}
