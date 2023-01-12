package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.CatchExceptionVisitor;
import com.ensao.gi5.lint.wrapper.CatchExceptionWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CatchExceptionRule extends Rule{
    public CatchExceptionRule() {
        super(Constantes.LINT_REG_015, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));


            List<CatchExceptionWrapper> catchExceptions = new ArrayList<>();
            new CatchExceptionVisitor().visit(unit, catchExceptions);

            for (CatchExceptionWrapper catchException: catchExceptions) {
                final Violation violation = new Violation();
                violation.setDescription("exception catched without log ");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(catchException.getLine());
                addViolation(violation);
            }

        } catch (Exception e) {
        }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
