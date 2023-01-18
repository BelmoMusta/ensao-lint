package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.IfElseWrapper;

public class IfElseRule extends Rule {
    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<IfElseWrapper> ifElse = new LinkedHashSet<>();
        compilationUnit.accept(new IfElseVisitor(), ifElse);
        for (IfElseWrapper ifElseWrapper : ifElse) {
            if (!ifElseWrapper.hasBraces()) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("if and else statements must have braces");
                violation.setLine(ifElseWrapper.getLine());
                addViolation(violation);
            }
        }

    }
    @Override
    public boolean isActive() {
        return true;
    }
}
