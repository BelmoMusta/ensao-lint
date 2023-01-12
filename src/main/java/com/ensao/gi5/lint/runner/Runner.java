package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {

        if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }

        String directory = null;

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
        linter.registerRule(new UnusedImportsRule());
        linter.registerRule(new ParseErrorRule());
        linter.registerRule(new ClassNameRule());
        linter.registerRule(new LocalVariablesRule());
        linter.registerRule(new AttributesRule());
        linter.registerRule(new MethodLengthRule());

        linter.registerRule(new NumberOfMethodsByClassRule());
        linter.registerRule(new AttributesVisibilityRule());
        linter.registerRule(new UnusedPrivateMethodsRule());
        linter.registerRule(new IfElseRule());
        linter.registerRule(new ConstantsRule());
        linter.registerRule(new EnumerationRule());
        linter.registerRule(new AnonymousRule());
        linter.registerRule(new LambdaRule());
        linter.registerRule(new ReturnCountRule());
        linter.registerRule(new NumberOfParametersRule());
        linter.registerRule(new CatchExceptionRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        linter.run();
        //Genration of output file
        Collection<Violation> violations = linter.getAllViolations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please chose the output format");
        System.out.println("JSON | CSV | HTML | MarkDown");
        String format = scanner.nextLine();
        File outputFile = null;
        System.out.println("violations."+format.toLowerCase());
        FileWriter writer = null;
            switch (format) {
                case "JSON":
                    outputFile = new File("violations.json");
                    writer = new FileWriter(outputFile);
                    Utils.jsonOutputFile(violations, writer);
                    break;
                case "CSV":
                    outputFile = new File("violations.csv");
                    writer = new FileWriter(outputFile);
                    Utils.csvOutputFile(violations, writer);
                    break;
                case "MD":
                    outputFile = new File("violations.md");
                    writer = new FileWriter(outputFile);
                    Utils.markdownOutputFile(violations, writer);
                    break;
                case "HTML":
                    outputFile = new File("violations.html");
                    writer = new FileWriter(outputFile);
                    Utils.htmlOutputFile(violations, writer);
                    break;
                default:
                    outputFile = new File("violations.txt");
                    writer = new FileWriter(outputFile);
                    Utils.txtOutputFile(violations, writer);
                    System.out.println("output not possible");

    }
}
}
