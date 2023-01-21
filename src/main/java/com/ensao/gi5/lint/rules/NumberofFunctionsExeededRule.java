package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodeWrapper;
import java.util.List;
import java.util.stream.Collectors;


public class NumberofFunctionsExeededRule extends Rule{

    public NumberofFunctionsExeededRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        
        final List<MethodeWrapper> functionsList = compilationUnit.getNumberOfFunctions()
                .stream()
                .map(MethodeWrapper::new)
                .collect(Collectors.toList());
        if(functionsList.size()>20)
        {
                final Violation violation = new Violation();
                violation.setDescription("Number of functions exeeds 20");
                violation.setFileName(compilationUnit.getFileName());
                addViolation(violation);
        }
        
    }

    @Override
    public boolean isActive() {
        return true;
    }
    
}
