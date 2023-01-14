package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.*;
import com.ensao.gi5.lint.rules.*;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le format output souhaité : ");

        String directory = "testFiles/normalExecution/Example.java";
        String outputType = scanner.nextLine();


        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            if (argument.equals("-s")) {
                if (i == args.length - 1 || (directory = args[i + 1]).isEmpty()) {
                    System.out.println("Usage example : -s  testFiles/normalExecution/Example.java");
                    throw new IllegalStateException("The directory or file are not specified");
                }
            } else if (argument.equals("-o")) {
                if (i == args.length - 1 || (outputType = args[i + 1]).isEmpty()) {
                    System.out.println("Usage example : -o json");
                    throw new IllegalStateException("The output type is not specified");
                }
            }
        }

        final Linter linter = new Linter();
        linter.registerRule(new UnusedImportsRule());
        linter.registerRule(new CheckExpression());
        linter.registerRule(new TypeStartWIthMajWithoutUnderscore());
        linter.registerRule(new NumberParametersRule());
        linter.registerRule(new ClearVisibilityOfClasses());
        linter.registerRule(new MethodBody());
        linter.registerRule(new MethodsNumbersLessThanTwinty());

        linter.registerSource(directory);

        switch (outputType) {
            case "console":
                linter.registerPrinter(new ConsolePrinter());
                break;
            case "csv":
                linter.registerPrinter(new CSVFilePrinter());
                break;
            case "json":
                linter.registerPrinter(new JSONFilePrinter());
                break;
            case "html":
                linter.registerPrinter(new HTMLFilePrinter());
               break;
            case "markdown":
                linter.registerPrinter(new MARKDOWNFilePrinter());
                break;
            default:
                System.out.println("Invalid output type, valid options are: console, csv, json, html, markdown");
                return;
        }

        linter.run();
    }
}
