package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodsInTotalRule extends Rule{

    public MethodsInTotalRule(){
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();

        if(methodDeclarations.size()>20){
            Violation violation = new Violation();
            violation.setRuleId(Constantes.LINT_REG_011);
            violation.setFileName(compilationUnit.getFileName());
            violation.setDescription("Total number of methods should not be more than 20 ");
            violation.setLevel(Level.HIGHEST);
            violations.add(violation);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
