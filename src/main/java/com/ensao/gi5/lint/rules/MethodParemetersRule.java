package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

import java.util.List;

public class MethodParemetersRule extends Rule{

    public MethodParemetersRule(){
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();
        List<ConstructorDeclaration> constructorDeclarations = compilationUnit.getConstructors();

        for(MethodDeclaration method : methodDeclarations){

            List<Parameter> parameters = compilationUnit.getParameters(method);

            if(parameters.size() > 2){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(method.getBegin().get().line);
                violation.setRuleId(Constantes.LINT_REG_012);
                violation.setLevel(Level.HIGHEST);
                violation.setDescription("Method " + method.getName() + " must not have more than 2 parameters");
                violations.add(violation);
            }
        }

        for(ConstructorDeclaration constructor : constructorDeclarations){

            if(compilationUnit.getParameters(constructor).size() > 2){
                Violation violation = new Violation();
                violation.setRuleId(Constantes.LINT_REG_012);
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(constructor.getBegin().get().line);
                violation.setLevel(Level.HIGHEST);
                violation.setDescription("Constructor " + constructor.getName() + " must not have more than 2 parameters");
                violations.add(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
