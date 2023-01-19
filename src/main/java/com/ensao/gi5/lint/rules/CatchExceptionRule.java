package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.CatchExceptionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This code defines a rule class called "CatchExceptionRule" that extends the "Rule" class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for caught exceptions using the "CatchExceptionVisitor" visitor class.
 * The "CatchExceptionVisitor" check for CatchClause in the method, for each catchClause it checks whether
 * the catch block contains log statement or not, if it doesn't, it adds a new "RuleWrapper" object to the a list,
 * with the name of the exception and the line number of the catch.
 * The "apply" method iterates the list of caught exceptions and creates a new "Violation" object for each exception
 * with the name of the file, a message, and the line number of the exception as arguments. It then adds the violation
 * to the list of violations.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class CatchExceptionRule extends Rule{
    public CatchExceptionRule() {
        super(Constantes.LINT_REG_015, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleWrapper> catchExceptions = new ArrayList<>();
            compilationUnit.accept(new CatchExceptionVisitor(catchExceptions),null);
            for (RuleWrapper catchException: catchExceptions) {
                Violation violation = ViolationMaker.makeViolation(
                        compilationUnit.getFileName(),
                        "exception catched without log ",
                        catchException.getLine()
                );
                addViolation(violation);
            }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
