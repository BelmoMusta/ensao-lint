package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.*;
import com.ensao.gi5.lint.rules.*;

public class Runner {
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }

        String directory = null;
        String outputType = null;

        for (int i = 0; i < args.length; i++) {
            String argument = args[i];
            if (argument.equals("-s")) {
                if (i == args.length - 1 || (directory = args[i + 1]).isEmpty()) {
                    System.out.println("Usage example : -s  D:/Test/Example.java");
                    throw new IllegalStateException("The directory or file are not specified");
                }
            }
            else if (argument.equals("-o")) {
                if (i == args.length - 1 || (outputType = args[i + 1]).isEmpty()) {
                    System.out.println("Usage example : -o json");
                    throw new IllegalStateException("The output type is not specified");
                }
            }
        }
        final Linter linter = new Linter();
        linter.registerRule(new UnusedImportsRule());
        linter.registerRule(new ClassAttributesStartWithLowerCase());
        linter.registerRule(new LocalVariablesStartWithLowerCase());
        linter.registerRule(new EnumsShouldBeAllUpperCaseAndUnderscoreSeparator());
        linter.registerRule(new CatchLogExceptions());
        linter.registerRule(new MethodAndConstructorParams());
        linter.registerRule((new ClassAttributeVisibility()));

        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        switch (outputType) {
            case "console":
                linter.registerPrinter(new ConsolePrinter());
                break;
            case "csv":
                linter.registerPrinter(new CSVFileWriter());
                break;
            case "json":
                linter.registerPrinter(new JsonPrinter());
                break;
            case "html":
                linter.registerPrinter(new HtmlPrinter());
                break;
            case "markdown":
                linter.registerPrinter(new MarkdownPrinter());
                break;
            default:
                System.out.println("Invalid output type, valid options are: console, csv, json, html, markdown");
                return;
        }
        linter.run();
    }
}
