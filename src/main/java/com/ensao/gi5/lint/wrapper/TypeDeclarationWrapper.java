package com.ensao.gi5.lint.wrapper;

import java.lang.reflect.Modifier;
import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeDeclarationWrapper {
    private TypeDeclaration typeDeclaration;
    private String fileName;

    public TypeDeclarationWrapper(TypeDeclaration typeDeclaration, String fileName) {
        this.typeDeclaration = typeDeclaration;
        this.fileName = fileName;
    }

    public String getName() {
        return typeDeclaration.getName().asString();
    }

    public String getFileName() {
        return fileName;
    }



    public boolean isPrivate() {
        return typeDeclaration.getModifiers().contains(Modifier.PRIVATE);
    }

    public boolean isProtected() {
        return typeDeclaration.getModifiers().contains(Modifier.PROTECTED);
    }

    public boolean isPublic() {
        return typeDeclaration.getModifiers().contains(Modifier.PUBLIC);
    }
}
