package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class AttributeVisibilityRule extends Rule{

    public AttributeVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<AttributeWrapper> attributes = new ArrayList<>();
        compilationUnit.accept(new AttributeVisitor(), attributes);
        for (AttributeWrapper attribute: attributes) {
            if (attribute.getAccessModifier().isEmpty()) {
                buildViolationThenAddToCollection(
                        attribute.getLine(),
                        attribute + " should have an access modifier declared",
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
