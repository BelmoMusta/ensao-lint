package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class MarkdownPrinter implements Printer{

    private static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public void printViolations(Collection<Violation> violations) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("violations.md"));
        ) {
            writer.write("| LEVEL | RULE | DESCRIPTION |");
            writer.write(NEW_LINE_SEPARATOR);
            writer.write("| ----- | ---- | ----------- |");
            writer.write(NEW_LINE_SEPARATOR);

            for (Violation violation : violations) {
                writer.write("| " + violation.getLevel() + " | " + violation.getRuleId() + " | " + violation.getDescription() + " |");
                writer.write(NEW_LINE_SEPARATOR);
            }

            writer.flush();
        } catch (IOException e) {
            writer.write("An error occured while trying to load file");
        }
    }

}

