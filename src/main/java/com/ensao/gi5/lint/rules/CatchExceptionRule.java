package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.CatchExceptionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class CatchExceptionRule extends Rule{
    public CatchExceptionRule() {
        super(Constantes.LINT_REG_015, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
