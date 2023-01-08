package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.lang.reflect.Field;
import java.util.List;

public class ClassFieldNamingRule extends Rule {


    public ClassFieldNamingRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }


    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final List<FieldDeclaration> fieldDeclarations = compilationUnit.getFields();
        for (FieldDeclaration fd : fieldDeclarations) {
            List<VariableDeclarator> variableDeclarators = fd.getVariables();
            for (VariableDeclarator vd : variableDeclarators) {
                if (!Character.isLowerCase(vd.getNameAsString().charAt(0))) {
                    Violation violation = new Violation();
                    violation.setDescription("Class attribute '" + vd.getNameAsString() + "' must start with lower case");
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
