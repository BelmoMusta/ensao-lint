package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.util.Collection;

public class ConsolePrinter implements Printer {
	@Override
	public void printViolations(Collection<Violation> violations) {
		for (Violation violation : violations) {
			String level = String.format("%s%s ", ConsoleColors.PURPLE.getColor(), violation.getLevel());
			String rule = String.format("%s[%s]%s : ", ConsoleColors.GREEN.getColor(), violation.getRuleId(),
					ConsoleColors.RESET.getColor());
			String filenameAndLine = String.format(" at '%s%s:%s%s'", ConsoleColors.BLUE_UNDERLINED.getColor(),
					violation.getFileName(), violation.getLine(), ConsoleColors.RESET.getColor());
			System.out.println(level + rule + violation.getDescription() + filenameAndLine);
		}

	}
}
