package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class HTMLFilePrinter implements Printer{
    @Override
    public void printViolations(Collection<Violation> violations) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("violations.html"));
        ) {
            writer.write("<html><head><title>Violations</title></head><body><table>");
            writer.write("<tr><th>LEVEL</th><th>RULE</th><th>DESCRIPTION</th></tr>");

            for (Violation violation : violations) {
                writer.write("<tr><td>" + violation.getLevel() + "</td><td>" + violation.getRuleId() + "</td><td>" + violation.getDescription() + "</td></tr>");
            }

            writer.write("</table></body></html>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
