package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.AttributeRule;
import com.ensao.gi5.lint.rules.CheckTypeRule;
import com.ensao.gi5.lint.rules.ConstantRule;
import com.ensao.gi5.lint.rules.EnumRule;
import com.ensao.gi5.lint.rules.LineRule;
import com.ensao.gi5.lint.rules.OperandExpressionRule;
import com.ensao.gi5.lint.rules.ParseErrorRule;
import com.ensao.gi5.lint.rules.UnusedImportsRule;

public class Runner {
    public static void main(String[] args) {
    	
        String directory = "C:/Users/user/Documents/projet-java/ensao-lint/testFiles/normalExecution/Example.java";

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
        linter.registerRule(new OperandExpressionRule());
        linter.registerRule(new CheckTypeRule());
        linter.registerRule(new AttributeRule());
        linter.registerRule(new ConstantRule());
        linter.registerRule(new EnumRule());
        linter.registerRule(new LineRule());
        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        linter.run();
    }
}
