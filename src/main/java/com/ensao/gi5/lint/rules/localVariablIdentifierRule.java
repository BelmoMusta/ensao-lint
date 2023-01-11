package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class localVariablIdentifierRule extends Rule{

    public localVariablIdentifierRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

              for(FieldDeclaration var:compilationUnit.getLocalVars())
           {
               // if it is a local variable (not static) and do not start with an upper case
               if(!var.getModifiers().toString().contains("static") && !var.getModifiers().toString().contains("final") && !var.getVariable(0).getNameAsString().matches("^[a-z]*$"))
               {
                   final Violation violation = new Violation();
                   violation.setDescription("Local variable must start with a lowercase letter in '" + var + "'");
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
