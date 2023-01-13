package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.sun.org.apache.bcel.internal.Const;

import java.util.List;

public class ClassConstantesRule extends Rule{

    public ClassConstantesRule(){
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<FieldDeclaration> attributes = compilationUnit.getAttributes();

        for(FieldDeclaration element : attributes){

            if(element.isStatic() && element.isFinal()){

                for(VariableDeclarator variable : compilationUnit.getVariables(element)){

                    if(variable.getNameAsString().matches("^[A-Z][A-Z_]*")){
                        Violation violation = new Violation();
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(variable.getBegin().get().line);
                        violation.setLevel(Level.MEDIUM);
                        violation.setDescription("Constant " + variable.getNameAsString() + " must be in Upper case and separated by _");
                        violations.add(violation);
                    }
                }

            }
        }

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
