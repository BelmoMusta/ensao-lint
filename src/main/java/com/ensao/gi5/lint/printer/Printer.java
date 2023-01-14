package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;

public interface Printer extends Comparable<Printer> {
    void printViolations(Collection<Violation> violations) throws IOException;

    @Override
    default int compareTo(Printer aPrinter) {
        return Comparator.comparing(printer -> printer.getClass().getName()).compare(this, aPrinter);
    }
}
