package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.EnumerationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called EnumerationRule that extends the Rule class.
 * It is used to check for naming conventions in enumerations in Java code.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for caught exceptions using the "EnumerationVisitor" visitor class.
 * The visitor check the name of each enum constant in the EnumDeclaration and if the name is not in all uppercase or doesn't
 * contain an underscore, it adds a new RuleWrapper object to the enumerationNaming list with the name of the constant
 * and the line number where it is located in the source code.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class EnumerationRule extends Rule{
    public EnumerationRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
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

    @Override
    public boolean isActive() {
        return true;
    }
}
