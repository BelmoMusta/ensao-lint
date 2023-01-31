package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantesAttributesVisitor;
import com.ensao.gi5.lint.visitor.EnumElementsDeclarationVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class EnumElementsDeclarationRule extends Rule{

    public EnumElementsDeclarationRule(){
        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<ClassAttributesWrapper> enumConstantesSet = new LinkedHashSet<ClassAttributesWrapper>();
        compilationUnit.accept(new EnumElementsDeclarationVisitor(),enumConstantesSet);

        enumConstantesSet.stream()
                .filter(field -> !field.toString().matches("^[A-Z][A-Z_]*"))
                .forEach(field -> {
                    final Violation violation = new Violation();
                    violation.setDescription(field.toString() + "n'est pas en majuscule'");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(field.getLine());
                    addViolation(violation);
                });
    }



    @Override
    public boolean isActive() {
        return false;
    }
}
