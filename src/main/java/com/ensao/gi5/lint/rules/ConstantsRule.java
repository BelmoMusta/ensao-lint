package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ConstantVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This code defines a rule class called "ConstantsRule" that extends the "Rule" class.
 *  The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for constants in the code using the "ConstantVisitor" visitor class.
 * The method creates a new empty list called "constantNaming" and passes it as an argument to the "ConstantVisitor" class,
 * then it checks the size of the list of constants, if the size is not equal to 0, it iterates over the list of constants
 * and creates a new "Violation" object for each constant with the name of the file, a message, and the line number of the constant
 * as arguments. It then adds the violation to the list of violations.
 * The "ConstantVisitor"  check if they are final constants. If a FieldDeclaration is a final constant,
 * it checks the naming of the constant and adds a RuleWrapper object to a list of RuleWrappers if the naming does not follow
 * certain conventions (starts with lowercase or does not contain an underscore).
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class ConstantsRule extends Rule{
    public ConstantsRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
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

    @Override
    public boolean isActive() {
        return true;
    }
}
