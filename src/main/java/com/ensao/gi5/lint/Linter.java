package com.ensao.gi5.lint;

import com.ensao.gi5.lint.printer.Printer;
import com.ensao.gi5.lint.rules.Rule;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Linter implements Runnable {
    private Set<Rule> rules = new LinkedHashSet<>();
    private Set<Printer> printers = new HashSet<>();
    private Set<File> sources = new TreeSet<>();

    public void registerSource(File source) {
        if (source.isFile()) {
            sources.add(source);
        } else {
            sources.addAll(Utils.getFilesFromDirectory(source));
        }
    }

    public void registerSource(String directoryPath) {
        registerSource(new File(directoryPath));
    }

    public void registerRule(Rule rule) {
        rules.add(rule);
    }

    public void registerPrinter(Printer printer) {
        printers.add(printer);
    }

    @Override
    public void run() {
        final List<CompilationUnitWrapper> compilationUnitWrappers = new ArrayList<>();
        for (File source : sources) {
            compilationUnitWrappers.add(Utils.getCompilationUnit(source));
            System.out.println(Utils.getCompilationUnit(source));
        }

        for (CompilationUnitWrapper compilationUnit : compilationUnitWrappers) {
            for (Rule rule : rules) {
                if (rule.isActive()) {
                    rule.apply(compilationUnit);
                }
            }
        }

        for (Printer printer : printers) {
            printer.printViolations(getAllViolations());
        }
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public Set<Printer> getPrinters() {
        return printers;
    }

    public Set<File> getSources() {
        return sources;
    }

    public Collection<Violation> getAllViolations() {
        return rules.stream().flatMap(rule -> rule.getViolations().stream())
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
