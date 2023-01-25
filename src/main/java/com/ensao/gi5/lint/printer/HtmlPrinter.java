package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public class HtmlPrinter implements Printer {


    @Override
    public void printViolations(Collection<Violation> violations) throws IOException {

         Writer writer = new FileWriter("violations.html");

            for (Violation violation : violations) {
                writer.write("<tr><td>" + violation.getLevel() + "</td><td>" + violation.getRuleId() + "</td><td>" + violation.getDescription() + "</td></tr>");
            }

            writer.flush();

    }

    @Override
    public int compareTo(Printer aPrinter) {
        return Printer.super.compareTo(aPrinter);
    }
}
