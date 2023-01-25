package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public class MarkdownPrinter implements Printer{


    @Override
    public void printViolations(Collection<Violation> violations) throws IOException {
        Writer writer = new FileWriter("violations.md");
        for (Violation violation : violations) {
            writer.write("| " + violation.getLevel() + " | " + violation.getRuleId() + " | " + violation.getDescription() + " |");
            writer.append('\n');
        }
    }

    @Override
    public int compareTo(Printer aPrinter) {
        return Printer.super.compareTo(aPrinter);
    }
}