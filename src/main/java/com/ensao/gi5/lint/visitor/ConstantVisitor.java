package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

public class ConstantVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private List<RuleWrapper> constantNaming;
    public ConstantVisitor(List<RuleWrapper> constantNaming){
        this.constantNaming = constantNaming;
    }
    @Override
    public void visit(FieldDeclaration field, List<RuleWrapper> arg) {
        super.visit(field, arg);
            boolean isFinal = field.getModifiers().contains(Modifier.finalModifier());
            if(isFinal) {
                List<VariableDeclarator> variables = field.getVariables();
                for (VariableDeclarator variable :variables
                ) {
                    String variableName = variable.getNameAsString();
                    boolean containUnderscore = variableName.contains("_");
                    if (Character.isLowerCase(variableName.charAt(0)) || !containUnderscore) {
                        constantNaming.add(new RuleWrapper(variableName,field.getRange().isPresent()?field.getRange().get().begin.line:0));
                    }
                }

            }
    }
}
