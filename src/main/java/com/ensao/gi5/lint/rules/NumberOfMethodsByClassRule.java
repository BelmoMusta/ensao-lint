package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.File;
import java.util.List;

public class NumberOfMethodsByClassRule extends Rule {
    public NumberOfMethodsByClassRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<MethodDeclaration> methods = unit.findAll(MethodDeclaration.class);
            if (methods.size() > 20) {
                final Violation violation = new Violation();
                violation.setDescription("Number of methods exceeds 20");
                violation.setFileName(compilationUnit.getFileName());
                int line = methods.get(0).getBegin().get().line;
                violation.setLine(line);
                addViolation(violation);
            }
        } catch (Exception e) {
            // Handle exception
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}