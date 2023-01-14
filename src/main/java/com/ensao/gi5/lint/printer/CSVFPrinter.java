package com.ensao.gi5.lint.printer;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import com.ensao.gi5.lint.rules.violations.Violation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVFPrinter implements Printer {

    @Override
    public void printViolations(Collection<Violation> violations) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("violations.csv"));
        ) {
            writer.write("LEVEL, RULE, DESCRIPTION");
            writer.newLine();
            for (Violation violation : violations) {
                writer.write(violation.getLevel() + ", " + violation.getRuleId() + ", " + violation.getDescription());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}