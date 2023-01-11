package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.EnumVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.enumeration.EnumWrapper;

public class EnumRule extends Rule{
	
	public EnumRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<EnumWrapper> EnumWrappers = new ArrayList<>();
        compilationUnit.accept(new EnumVisitor(), EnumWrappers);
        
        EnumWrappers.forEach(enumWrapper -> enumWrapper.getElements().forEach(enumElement ->{
            if(!enumElement.getName().matches("[A-Z_]+")){
            	final Violation violation = new Violation();
                violation.setDescription("The elements of an enumeration are in uppercase, with _ as separator" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(enumElement.getLigne());
                addViolation(violation);
            }
        }));
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
