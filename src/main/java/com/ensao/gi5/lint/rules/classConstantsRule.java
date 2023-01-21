package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;


public class classConstantsRule extends Rule{

    public classConstantsRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

         for(FieldDeclaration var:compilationUnit.getLocalVars())
           {
               // if it is a class attribute (final) and is not upper case
               if(var.getModifiers().toString().contains("final") && !var.getVariable(0).getNameAsString().matches("^[A-Z]+$"))
               {
                   Violation violation = new Violation();
                   violation.setDescription("The constants of a class must be written in upper case'" + var + "'");
                   violation.setFileName(compilationUnit.getFileName());
                   violation.setLine(var.getBegin().get().line);
                   addViolation(violation);
               }
            }
    }

    @Override
    public boolean isActive() {
        return true;
    }
    
}
