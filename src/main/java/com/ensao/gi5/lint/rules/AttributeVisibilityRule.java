package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.util.List;

public class AttributeVisibilityRule extends Rule {

    public AttributeVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }



    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<FieldDeclaration> fieldDeclarationList = compilationUnit.getFields();
        for (FieldDeclaration fd : fieldDeclarationList) {
            if (fd.getModifiers().isEmpty()) {
                Violation violation = new Violation();
                violation.setDescription("Attribute '" + fd.getVariable(0).getNameAsString() +"' must have visibility accessor");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(fd.getVariable(0).getBegin().get().line);
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
