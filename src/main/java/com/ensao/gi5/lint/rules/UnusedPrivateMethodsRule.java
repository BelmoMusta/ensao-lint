package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.UnusedPrivateMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class UnusedPrivateMethodsRule extends Rule{
    public UnusedPrivateMethodsRule() {
        super(Constantes.LINT_REG_017, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            List<RuleWrapper> unsedMethods = new ArrayList<>();
            compilationUnit.accept(new UnusedPrivateMethodsVisitor(unsedMethods), null);
            if(unsedMethods.size()!=0){
                for (RuleWrapper method : unsedMethods) {

                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Unused Private Methods",
                            method.getLine());
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
