package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.*;
import com.ensao.gi5.lint.rules.*;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

       /*if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }*/
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the desired file format: ");

        String outputType = scanner.nextLine();
        String directory = "C:/Users/lenovo/Documents/GitHub/ensao-lint-Wijdane-CHARKAOUI/testFiles/normalExecution/Example.java";

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
        linter.registerRule(new LambdaExpressionsRule());
        linter.registerRule(new ParamsNumberRule());
        linter.registerRule(new IfElseRule());
        linter.registerRule(new MethodBodyRule());
        linter.registerRule(new MethodsNumberRule());
        linter.registerRule(new AttributeVisibilityRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);

        switch (outputType) {
            case "console":
                linter.registerPrinter(new ConsolePrinter());
                break;
            case "csv":
                linter.registerPrinter(new CSVFPrinter());
                break;
            case "json":
                linter.registerPrinter(new JSONPrinter());
                break;
            case "html":
                linter.registerPrinter(new HTMLPrinter());
                break;
            case "markdown":
                linter.registerPrinter(new MarkdownPrinter());
                break;
            default:
                System.out.println("Invalid output type.");
                return;
        }

        linter.run();
    }
}
