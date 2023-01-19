package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * AttributesRule is a Java class that defines a rule for linting  of Java code.
 * It is a specific rule for analyzing attributes of a Java class.
 * It use an AttributeVisitor instance that iterates through each field and checks if the name starts with an uppercase character
 * and if the field is not final. If those conditions are met, it adds a new RuleWrapper object to the list of attributes
 * with the field name and line number as the arguments. the rule then checks the size of the attributes list,
 * if it is not empty, for each attribute in the list, it creates a Violation object with the file name,
 * the error message, and the line number of the attribute and add it to the list of violations.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class AttributesRule extends Rule{
    public AttributesRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
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

    @Override
    public boolean isActive() {
        return true;
    }
}
