package com.ensao.gi5.lint.printer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import com.ensao.gi5.lint.rules.violations.Violation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVFileWriter implements Printer {
    private static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public void printViolations(Collection<Violation> violations) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("violations.csv"));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            csvPrinter.printRecord("LEVEL", "RULE", "DESCRIPTION");

            for (Violation violation : violations) {
                csvPrinter.printRecord(violation.getLevel(), violation.getRuleId(), violation.getDescription());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
