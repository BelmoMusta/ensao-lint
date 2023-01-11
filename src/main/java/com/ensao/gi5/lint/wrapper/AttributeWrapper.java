package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class AttributeWrapper extends AbstractWrapper {

    private final String accessModifier;
    private final String fieldName;
    private final String className;

    public AttributeWrapper(FieldDeclaration fieldDeclaration) {
        super(Utils.getLine(fieldDeclaration));
        this.className = Utils.getTypeNameFromNodes(fieldDeclaration);
        this.fieldName = fieldDeclaration
                .getVariables()
                .getFirst()
                .orElse(new VariableDeclarator())
                .getNameAsString();
        this.accessModifier = fieldDeclaration.getAccessSpecifier().asString();
    }

    public String getAccessModifier() {
        return this.accessModifier;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public String toString() {
        return "Field '" + this.fieldName + "' of Class '" + this.className + "'";
    }
}
