package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.EnumerationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class EnumerationRule extends Rule{
    public EnumerationRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            List<RuleWrapper> enumerationNaming =new ArrayList<>();
            compilationUnit.accept(new EnumerationVisitor(enumerationNaming), null);
            if(enumerationNaming.size()!=0){
                for(RuleWrapper enumeration:enumerationNaming){
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Enumeration naming error",
                            enumeration.getLine()
                    );
                    addViolation(violation);
                }
            }
        }
        catch (Exception e){
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
