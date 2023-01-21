package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodeWrapper;
import java.util.Set;
import java.util.stream.Collectors;


public class argsSizeRule extends Rule{

    public argsSizeRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final Set<MethodeWrapper> functionsList = compilationUnit.getNumberOfFunctions()
                .stream()
                .filter(var-> var.getParameters().size()<=2) // le nombre de parametres ne doit pas dépasser 2
                .map(MethodeWrapper::new)
                .collect(Collectors.toSet());
        
       final Set<MethodeWrapper> allFunctions = compilationUnit.getNumberOfFunctions()
                .stream().map(MethodeWrapper::new)
                .collect(Collectors.toSet());

        for (MethodeWrapper f : allFunctions) {
            if (!functionsList.contains(f)) {
                final Violation violation = new Violation();
                violation.setDescription("The number of parametrs exeeds 2 '" + f + "'");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(f.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
    
}
