package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.beans.MethodDescriptor;
import java.util.List;

public class LocalVariableRule extends Rule{

    public LocalVariableRule(){
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();

        for(MethodDeclaration method : methodDeclarations){

            for(VariableDeclarator variable : compilationUnit.getlocalVariables(method)){

                if (!Character.isLowerCase(variable.getNameAsString().charAt(0))){
                    Violation violation = new Violation();
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLevel(Level.HIGH);
                    violation.setLine(variable.getBegin().get().line);
                    violation.setDescription("Local variable " + variable.getNameAsString() + " must be starting with a lower case");
                    violations.add(violation);
                }
            }
        }

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
