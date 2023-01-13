package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.UnusedImportsVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ImportWrapper;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UnusedImportsRule extends Rule {

    public UnusedImportsRule() {
        super(Constantes.LINT_REG_001, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<ImportWrapper> declaredImports = compilationUnit.getImports()
                .stream()
                .filter(imprt -> !imprt.isAsterisk())
                .filter(imprt -> !imprt.isStatic())
                .map(ImportWrapper::new)
                .collect(Collectors.toSet());


        final Set<ImportWrapper> usedImports = new LinkedHashSet<>();
        compilationUnit.accept(new UnusedImportsVisitors(), usedImports);


        for (ImportWrapper declaredImport : declaredImports) {
            if (!usedImports.contains(declaredImport)) {
                final Violation violation = new Violation();
                violation.setDescription("Unused import '" + declaredImport + "'");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(declaredImport.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
