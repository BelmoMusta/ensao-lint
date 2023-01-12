package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class AttributesRule extends Rule{
    public AttributesRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            List<RuleWrapper> attributes = new ArrayList<>();
            compilationUnit.accept(new AttributeVisitor(attributes),null);
            if(attributes.size()!=0){
                for(RuleWrapper attribute:attributes){
                    Violation violation =ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Attributes error",
                            attribute.getLine()
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
