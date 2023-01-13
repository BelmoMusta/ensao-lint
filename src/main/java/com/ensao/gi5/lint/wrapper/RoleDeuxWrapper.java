package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class RoleDeuxWrapper {
    private final String nom;
    private final int ligne;
    private final String type;

    public RoleDeuxWrapper(ClassOrInterfaceDeclaration roleDeux) {
        this.nom = roleDeux.getNameAsString();
        this.ligne = roleDeux.getBegin().map(pos -> pos.line).orElse(-1);
        this.type = roleDeux.isInterface() ? "Interface" : "Classe";

    }
    public RoleDeuxWrapper(EnumDeclaration enumDeclaration){
        this.nom = enumDeclaration.getNameAsString();
        this.ligne = enumDeclaration.getBegin().map(pos -> pos.line).orElse(-1);
        this.type = "Enum";
    }
    public RoleDeuxWrapper(AnnotationDeclaration annotationDeclaration){
        this.nom = annotationDeclaration.getNameAsString();
        this.ligne = annotationDeclaration.getBegin().map(pos -> pos.line).orElse(-1);
        this.type = "Annotation";
    }

    public String getNom() {
        return nom;
    }

    public int getLigne() {
        return ligne;
    }

    public String getType() {
        return type;
    }
}
