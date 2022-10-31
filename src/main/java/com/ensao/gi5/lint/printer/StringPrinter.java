package com.ensao.gi5.lint.printer;

import com.ensao.gi5.lint.rules.violations.Violation;

import java.io.StringWriter;
import java.util.Collection;

public class StringPrinter implements Printer {
    final StringWriter stringWriter;

    public StringPrinter(StringWriter stringWriter) {
        this.stringWriter = stringWriter;
    }

    @Override
    public void printViolations(Collection<Violation> violations) {
        for (Violation violation : violations) {
            stringWriter.write(violation.toString());
            stringWriter.write('\n');
        }
    }
}
