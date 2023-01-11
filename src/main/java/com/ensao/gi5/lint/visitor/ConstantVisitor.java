package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;
import java.util.List;

public class ConstantVisitor extends VoidVisitorAdapter<Void> {
    private Hashtable<String,Integer> constantNaming ;
    public ConstantVisitor(Hashtable<String,Integer> constantNaming){
        this.constantNaming = constantNaming;
    }
    @Override
    public void visit(FieldDeclaration field, Void arg) {
        super.visit(field, arg);
            boolean isFinal = field.getModifiers().contains(Modifier.finalModifier());
            if(isFinal) {
                List<VariableDeclarator> variables = field.getVariables();
                for (VariableDeclarator variable :variables
                ) {
                    String variableName = variable.getNameAsString();
                    boolean containUnderscore = variableName.contains("_");
                    if (Character.isLowerCase(variableName.charAt(0)) || !containUnderscore) {
                        constantNaming.put(variableName,field.getRange().isPresent()?field.getRange().get().begin.line:0);
                    }
                }

            }
    }
}
