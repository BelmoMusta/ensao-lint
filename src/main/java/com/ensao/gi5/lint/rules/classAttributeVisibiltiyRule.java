package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.lang.reflect.Modifier;
import java.util.EnumSet;


public class classAttributeVisibiltiyRule extends Rule{

    public classAttributeVisibiltiyRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

     private boolean hasVisibility(FieldDeclaration f) {        
        if (f.getModifiers().toString().contains("public") || f.getModifiers().toString().contains("private") ||  f.getModifiers().toString().contains("protected"))
            return true;
        else
            return false;
    }
     
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        
        
          for(FieldDeclaration var:compilationUnit.getLocalVars())
           {
               // if it is a class attribute (static)
               if(!hasVisibility(var))
               {
                   final Violation violation = new Violation();
                   violation.setDescription("Class attribute must have declared visibility '" + var + "'");
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
