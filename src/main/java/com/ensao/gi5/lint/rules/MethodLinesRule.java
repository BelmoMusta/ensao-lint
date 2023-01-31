package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

public class MethodLinesRule extends Rule{
    protected MethodLinesRule(String id, Level level) {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnitWrapper) {
        List<TypeDeclaration<?>> types = compilationUnitWrapper.getTypes();
        for (TypeDeclaration<?> type : types) {
            List<MethodDeclaration> methods = type.getMethods();
            for (MethodDeclaration method : methods) {
                int lineCount = method.getEnd().get().line - method.getBegin().get().line;
                if (lineCount > 30) {
                    String message = "The method " + method.getNameAsString() + " has a body that exceeds 30 lines.";
                    final Violation violation = new Violation();
                    violation.setDescription(message);
                    violation.setFileName(compilationUnitWrapper.getFileName());
                    violation.setLine(method.getBegin().get().line);
                    addViolation(violation);
                }
            }
        }
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
