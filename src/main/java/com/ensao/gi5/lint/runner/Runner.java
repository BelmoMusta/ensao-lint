package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.rules.*;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {

        if (args == null || args.length == 0) {
            System.out.println("Usage example -s  D:/Test/Example.java");
            throw new IllegalStateException("arguments are empty");
        }

        //Please replace the directory of tests here
        String directory = "testFiles";

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
        //new rules
        linter.registerRule(new IfElseRule());
        linter.registerRule(new CatchExceptionRule());
        linter.registerRule(new NamingRule());
        linter.registerRule(new LocalVariableNameRule());
        linter.registerRule(new ClassAttributsNameRule());
        linter.registerRule(new ClassConstantsNameRule());
        linter.registerRule(new EnumElementsRule());
        linter.registerRule(new MethodBodyRule());
        linter.registerSource(directory);
        linter.run();


        //Generation de fichier de Sortie
        /*Collection<Violation> violations = linter.getAllViolations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please chose the output format");
        System.out.println("CSV | HTML |MarkDown");
        String format = scanner.nextLine();
        File outputFile = new File("ResultFile."+format.toLowerCase());
            Writer writer = new FileWriter(outputFile);
            switch (format) {
                case "CSV":
                    Utils.writeCsv(violations, writer);
                    break;
                case "MD":
                    Utils.writeMarkdown(violations, writer);
                    break;
                case "HTML":
                    Utils.writeHtml(violations, writer);
                    break;
                default:
                    System.out.println("No output Found");


    }*/
    }
}
