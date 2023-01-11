package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodeWrapper;
import java.util.Set;
import java.util.stream.Collectors;


public class functionContentRule extends Rule{

    public functionContentRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            
       final Set<MethodeWrapper> functionsList = compilationUnit.getNumberOfFunctions()
                .stream()
                .filter(var-> var.getEnd().get().line-var.getBegin().get().line<=30) // le nombre de ligne ne doit pas dépasser 30
                .map(MethodeWrapper::new)
                .collect(Collectors.toSet());
       final Set<MethodeWrapper> allFunctions = compilationUnit.getNumberOfFunctions()
                .stream().map(MethodeWrapper::new)
                .collect(Collectors.toSet());

        for (MethodeWrapper f : allFunctions) {
            if (!functionsList.contains(f)) {
                final Violation violation = new Violation();
                violation.setDescription("The body of a function must not exceed 30 lines '" + f + "'");
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
