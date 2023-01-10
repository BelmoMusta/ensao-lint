package com.ensao.gi5.test.utils;

import java.io.File;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.rules.Rule;

public class Utils {

	public  static int getRuleViolationsNumber(Rule rule, File file) {
		final Linter linter = new Linter();
		linter.registerRule(rule);
		linter.registerSource(file);
		linter.run();
		Rule mainRule = linter.getRules().iterator().next();
		return mainRule.getViolations().size();
	}
}
