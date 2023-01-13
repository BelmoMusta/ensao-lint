package com.ensao.gi5.lint.runner;

import com.ensao.gi5.lint.Linter;
import com.ensao.gi5.lint.printer.CSVFileWriter;
import com.ensao.gi5.lint.printer.ConsolePrinter;
import com.ensao.gi5.lint.printer.HtmlPrinter;
import com.ensao.gi5.lint.printer.JsonPrinter;
import com.ensao.gi5.lint.rules.*;
import com.ensao.gi5.lint.util.Utils;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("Usage example : -s  D:/Test/Example.java");
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
        linter.registerRule(new NominationRule());
        linter.registerRule(new MembersRule());
        linter.registerRule(new ConstRule());
        linter.registerRule(new AnonymousRule());
        linter.registerRule(new LocalvarRule());
       linter.registerRule(new VisibilityAttrRule());
        linter.registerRule(new MethodbodyRule());
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file extension (html,json,csv,console) :");

        String f = myObj.nextLine();
        switch (f) {
            case "html" :
                linter.registerPrinter(new HtmlPrinter());
                break;
            case "json":
                linter.registerPrinter(new JsonPrinter());
            case "csv" :
                linter.registerPrinter(new CSVFileWriter());
            case "console":
                linter.registerPrinter(new ConsolePrinter());

        }
        linter.registerSource(directory);

        linter.run();
    }
}
