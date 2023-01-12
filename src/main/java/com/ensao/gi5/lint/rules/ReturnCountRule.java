package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ReturnCountVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ReturnCountWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReturnCountRule extends Rule{
    public ReturnCountRule() {
        super(Constantes.LINT_REG_014, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<ReturnCountWrapper> methodReturnCounts = new ArrayList<>();
            new ReturnCountVisitor().visit(unit, methodReturnCounts);
            for (ReturnCountWrapper methodReturnCount : methodReturnCounts) {
                if (methodReturnCount.getReturnCount() > 1) {
                        final Violation violation = new Violation();
                        violation.setDescription("number of returns exceeds 1");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(methodReturnCount.getLine());
                        addViolation(violation);
                }
            }
        } catch (Exception e) {
        }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
