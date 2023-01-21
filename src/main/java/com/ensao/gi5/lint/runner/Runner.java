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
// enter the file path
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
        linter.registerPrinter(new ConsolePrinter());
        linter.registerRule(new ClassOrInterfaceNameRule());
        linter.registerRule(new LocalVariableSyntaxRule());
        linter.registerRule(new ClassVariableNameRule());
        linter.registerRule(new ConstantClassNameRule());
        linter.registerRule(new BooleanExpressionRule());
        linter.registerRule(new MethodBodyLengthRule());
        linter.registerRule(new MethodCountRule());
        linter.registerRule(new MethodParametersRule());
        linter.registerRule(new ClassFieldVisibilityRule());
        linter.registerRule(new EnumElementRule());
        linter.registerRule(new ClausesRule());
        linter.registerRule(new UnusedVariableRule());


        linter.registerSource(directory);
        linter.run();
    }
}
