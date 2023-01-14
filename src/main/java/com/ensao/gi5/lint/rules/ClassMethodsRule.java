package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.visitor.TypeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class ClassMethodsRule extends Rule {

    public ClassMethodsRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<TypeWrapper> typesDeclared = new ArrayList<>();
        compilationUnit.accept(new TypeVisitor(), typesDeclared);
        for (TypeWrapper type: typesDeclared) {
            if (type.getMethodsNumber() > 20) {
                buildViolationThenAddToCollection(
                        type.getLine(),
                        type + " shouldn't have more than 20 methods declared inside of it",
                        compilationUnit.getFileName()
                );
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
