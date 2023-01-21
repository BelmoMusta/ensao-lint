package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.enumerationWrapper;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import java.util.Set;
import java.util.stream.Collectors;


public class enumerationRule extends Rule{

    
    public enumerationRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

   public boolean isUpper(NodeList<EnumConstantDeclaration> enume){
        boolean statut = true;
        for(EnumConstantDeclaration e:enume)
            if(!e.getNameAsString().toUpperCase().equals(e.getNameAsString()))
            {
                statut=false;
            }
        return statut;
    }
    
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        
         final Set<enumerationWrapper> enumes = compilationUnit.getEnums()
                .stream()
                .filter(enume -> !isUpper(enume.getEntries())) // elle ne doivent pas contenir "_"
                .map(enumerationWrapper::new)
                .collect(Collectors.toSet());
                
        final Set<enumerationWrapper> allEnumes = compilationUnit.getEnums()
                .stream()
                .map(enumerationWrapper::new)
                .collect(Collectors.toSet());
        
 for (enumerationWrapper e : allEnumes) {
            if (enumes.contains(e)) {
                final Violation violation = new Violation();
                violation.setDescription("The elements of an enumeration must be in upper case '" + e + "'");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(e.getLine());
                addViolation(violation);
            }
        }        

    }

    @Override
    public boolean isActive() {
        return true;
    }
    
}
