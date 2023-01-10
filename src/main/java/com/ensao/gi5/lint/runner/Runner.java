package com.ensao.gi5.lint.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributeNameRule;
import com.ensao.gi5.lint.rules.ConstantesRule;
import com.ensao.gi5.lint.rules.LocalVariableNameRule;
import com.ensao.gi5.lint.rules.ParseErrorRule;
import com.ensao.gi5.lint.rules.TypeNameRule;
import com.ensao.gi5.lint.rules.UnusedImportsRule;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;

public class Runner {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("Usage example -s  D:/Test/Example.java");
			throw new IllegalStateException("arguments are empty");
		}

		String directory = "C:\\Users\\MOHAMEDKH\\Documents\\Mr_Belmokhtar_Spring\\linter-test";

		for (int i = 0; i < args.length; i++) {
			String argument = args[i];
			if (argument.equals("-s")) {
				if (i == args.length - 1 || (directory = args[i + 1]).isEmpty()) {
					System.out.println("Usage example : -s  D:/Test/Example.java");
					throw new IllegalStateException("The directory or file are not specified");
				}
			}
		}
		final Linter linter = new Linter();
		linter.registerRule(new ParseErrorRule());
		linter.registerRule(new UnusedImportsRule());
		linter.registerRule(new TypeNameRule());
		linter.registerRule(new LocalVariableNameRule());
		linter.registerRule(new AttributeNameRule());
		linter.registerRule(new ConstantesRule());
		linter.registerPrinter(new ConsolePrinter());
		linter.registerSource(directory);
		linter.run();
		System.out.println();
		System.out.println();
		System.out.println("************************************************************");
		Collection<Violation> violations = linter.getAllViolations();
		Scanner scanner = new Scanner("CSV");
		System.out.print("Choisissez un format de sortie (CSV, JSON, HTML, Markdown) : ");
		String format = scanner.nextLine();
		File outputFile = new File("outputFiles/output." + format.toLowerCase());
		try {
			Writer writer = new FileWriter(outputFile);
			switch (format) {
			case Constantes.CSV:
				Utils.writeCsv(violations, writer);
				break;
			case Constantes.JSON:
				Utils.writeJson(violations, writer);
				break;
			case Constantes.HTML:
				Utils.writeHtml(violations, writer);
				break;
			case Constantes.MARCKDOWN:
				Utils.writeMarkdown(violations, writer);
				break;
			default:
				System.out.println("Format de sortie non reconnu");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
