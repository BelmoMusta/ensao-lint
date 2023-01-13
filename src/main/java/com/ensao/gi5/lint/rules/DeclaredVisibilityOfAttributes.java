package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.util.List;

public class DeclaredVisibilityOfAttributes extends Rule{

    public DeclaredVisibilityOfAttributes(){
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<FieldDeclaration> fieldDeclarations = compilationUnit.getAttributes();

        for(FieldDeclaration attribute : fieldDeclarations){

            if(attribute.getModifiers().isEmpty()){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(attribute.getBegin().get().line);
                violation.setLevel(Level.HIGHEST);
                violation.setDescription("Field must have a declared visibility");
                violations.add(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
