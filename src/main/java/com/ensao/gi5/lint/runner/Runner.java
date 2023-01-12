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
        linter.registerRule(new ParseErrorRule()); //LINT_REG_000
        linter.registerRule(new UnusedImportsRule()); //LINT_REG_001
        linter.registerRule(new javaTypesIdentifierRule()); //LINT_REG_002
        linter.registerRule(new localVariablIdentifierRule()); //LINT_REG_003
        linter.registerRule(new classAttributeRule()); //LINT_REG_004
        linter.registerRule(new classConstantsRule()); //LINT_REG_005
        linter.registerRule(new enumerationRule()); //LINT_REG_007
        linter.registerRule(new functionContentRule()); //LINT_REG_008
        linter.registerRule(new NumberofFunctionsExeededRule()); //LINT_REG_011
        linter.registerRule(new argsSizeRule()); //LINT_REG_012
        linter.registerRule(new classAttributeVisibiltiyRule());//LINT_REG_013
   

        linter.registerPrinter(new ConsolePrinter());
        linter.registerSource(directory);
        linter.run();
    }
}
