package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

import java.util.List;

public class EnumElementRule extends Rule {

    public EnumElementRule(){
        super(Constantes.LINT_REG_007,Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<EnumDeclaration> enumDeclarations = compilationUnit.getEnums();

        for(EnumDeclaration enume : enumDeclarations){
            List<EnumConstantDeclaration> elements = compilationUnit.getElements(enume);

            for(EnumConstantDeclaration element : elements){

                if(!element.toString().matches("^[A-Z_]+")){
                    Violation violation = new Violation();
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLevel(Level.LOW);
                    violation.setLine(element.getBegin().get().line);
                    violation.setDescription(enume.getName() +  "must have elements in upper case and separated by a _");
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
