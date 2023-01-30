package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.List;

public class AttributesNamingRule extends Rule{

    public AttributesNamingRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<FieldDeclaration> fieldDeclarations = compilationUnit.getAttributes();

        for(FieldDeclaration fieldDeclaration : fieldDeclarations){

            for(VariableDeclarator variable : fieldDeclaration.getVariables()){
                String name = variable.getNameAsString();

                if(!Character.isLowerCase(name.charAt(0))){

                    Violation violation = new Violation();
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(variable.getBegin().get().line);
                    violation.setLevel(Level.HIGH);
                    violation.setRuleId(Constantes.LINT_REG_004);
                    violation.setDescription(name + " is starting with an upper case , while expected to be in upper case");
                    violations.add(violation);
                }
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
