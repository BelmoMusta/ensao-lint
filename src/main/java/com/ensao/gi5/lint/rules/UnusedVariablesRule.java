package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.UnusedVariablesVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class UnusedVariablesRule extends Rule {
	
    public UnusedVariablesRule() {
        super(Constantes.LINT_REG_016, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<VariableWrapper> variableWrappers = new ArrayList<>();
        compilationUnit.accept(new UnusedVariablesVisitors(), variableWrappers);
        
        for(VariableWrapper variableWrapper: variableWrappers){

            if(variableWrapper.getUsageCount()==1){
                final Violation violation = new Violation();
                violation.setDescription("Unused variables should be deleted");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(variableWrapper.getLigne());
                violation.setRuleId(Constantes.LINT_REG_016);
                violation.setLevel(Level.MEDIUM);
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
