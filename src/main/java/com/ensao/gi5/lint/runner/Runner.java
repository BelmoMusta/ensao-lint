package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;

public class Runner {
    public static void main(String[] args) {

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
        linter.registerRule(new ParseErrorRule());
        linter.registerRule(new UnusedImportsRule());
        linter.registerRule(new NamingRule());
        linter.registerRule(new LowerCaseStartVariableRule());
        linter.registerRule(new LowerCaseStartAttributRule());
        linter.registerRule(new AttributVisibilityRule());
        linter.registerRule(new ConstantRule());
        linter.registerRule(new Methods20maxRule());
        linter.registerRule(new UnusedPrivateMethodsRule());
        linter.registerRule(new MethodLengthRule());
        linter.registerRule(new Method2ParameterRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        linter.run();
    }
}
