package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class classAttributeRule extends Rule{
    
    public classAttributeRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
       
            for(FieldDeclaration var:compilationUnit.getLocalVars())
           {
               // if it is a class attribute (static) and do not start with an upper case
               if(var.getModifiers().toString().contains("static") && !var.getVariable(0).getNameAsString().matches("^[a-z]*$"))
               {
                   final Violation violation = new Violation();
                   violation.setDescription("Class attribute must start with a lowercase letter in '" + var + "'");
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
