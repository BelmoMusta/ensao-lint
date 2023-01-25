package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.printer.CsvPrinter;
import com.ensao.gi5.lint.printer.HtmlPrinter;
import com.ensao.gi5.lint.printer.MarkdownPrinter;
import com.ensao.gi5.lint.rules.ClauseIfEseRule;
import com.ensao.gi5.lint.rules.ConstantVariablesOfClassesRule;
import com.ensao.gi5.lint.rules.LocalVariablesRule;
import com.ensao.gi5.lint.rules.UnusedImportsRule;
import com.ensao.gi5.lint.rules.violations.Violation;

import java.util.Collection;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }

        //String directory = null;
        String directory = "testFiles/normalExecution/IfElseExample.java";

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
//        linter.registerRule(new UnusedImportsRule());
//        linter.registerPrinter(new ConsolePrinter());
//
//        linter.registerRule(new LocalVariablesRule());
//        linter.registerRule(new ConstantVariablesOfClassesRule());
        linter.registerRule(new ClauseIfEseRule());
        //linter.registerSource("testFiles/normalExecution/IfElseExample.java");
        linter.registerSource(directory);

        //Generating the output file by choice
        Collection<Violation> violations = linter.getAllViolations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please chose the format of your output violations file : console | csv | html | md");
        String output = scanner.nextLine();
        switch(output){
            case "console":
                linter.registerPrinter(new ConsolePrinter());
                break;
            case "csv":
                linter.registerPrinter(new CsvPrinter());
                break;
            case "html":
                linter.registerPrinter(new HtmlPrinter());
                break;
            case "md":
                linter.registerPrinter(new MarkdownPrinter());
                break;
            default:
                linter.registerPrinter(new ConsolePrinter());
                System.out.println("Invalid output type, please choose from: console, csv, html, mmd");
                return;
        }
        linter.run();

    }
}
