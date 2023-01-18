package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeWrapper extends AbstractWrapper{
    private final String typeName;
    private final String typeNameFQN;
    private final int methodsNumber;
    private final String type;

    public TypeWrapper(TypeDeclaration<?> typeDeclaration) {
        super(Utils.getLine(typeDeclaration));
        this.methodsNumber = typeDeclaration.getMethods().size();
        this.typeName = typeDeclaration.getNameAsString();
        this.typeNameFQN = typeDeclaration.getFullyQualifiedName().orElse(typeName);
        this.type = Utils.getTypeName(typeDeclaration);
    }

    public String getTypeName() {
        return this.typeName;
    }

    public int getMethodsNumber() {
        return this.methodsNumber;
    }

    @Override
    public String toString() {
        return type + " '" + typeNameFQN + "'";
    }
}
