package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ConstantVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class ConstantsRule extends Rule{
    public ConstantsRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            List<RuleWrapper> constantNaming =new ArrayList<>();
            compilationUnit.accept(new ConstantVisitor(constantNaming), null);
            if(constantNaming.size()!=0){
                for(RuleWrapper constant:constantNaming){
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Constant naming error",
                            constant.getLine()
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
