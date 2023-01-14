package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;
//import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

import java.io.Writer;

import java.util.Collection;



public class CsvPrinter implements Printer{
    @Override
    public void printViolations(Collection<Violation> violations) throws IOException {

        //Writer writer;

//        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
//            writer.writeAll(csvData);
//        }
        Writer writer = new FileWriter("violations.csv");

        violations.stream().map(Violation::toString).forEach(v-> {
            try {
                writer.write(v);
                writer.append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
    }


            @Override
            public int compareTo (Printer aPrinter) {
                return Printer.super.compareTo(aPrinter);
            }
}

