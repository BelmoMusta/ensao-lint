package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class AttributeNameRule extends Rule {

    public AttributeNameRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<AttributeWrapper> attributes = new ArrayList<>();
        compilationUnit.accept(new AttributeVisitor(), attributes);
        for (AttributeWrapper attribute: attributes) {
            if (
                    Character.isUpperCase(attribute.getFieldName().charAt(0))
                    && isAttributeNotConstant(attribute)
            ) {
                buildViolationThenAddToCollection(
                        attribute.getLine(),
                        attribute + " should start with a lower case",
                        compilationUnit.getFileName()
                );
            }
        }
    }

    public boolean isAttributeNotConstant(AttributeWrapper attribute) {
        return !attribute.isStatic() && !attribute.isFinal();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
