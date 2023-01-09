package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.util.List;

public class ConstantNamingRule extends Rule {


    public ConstantNamingRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<FieldDeclaration> constants = compilationUnit.getConstants();
        for (FieldDeclaration fd : constants) {
            for (VariableDeclarator vd : fd.getVariables()) {
                if (vd.getNameAsString() != vd.getNameAsString().toUpperCase()) {
                    Violation violation = new Violation();
                    violation.setDescription("The constant '" + vd.getNameAsString() + "' should be upper case and separated with '_'");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(vd.getBegin().get().line);
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
