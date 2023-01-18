package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.VariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;
public class VariableRule extends Rule {
    public VariableRule() {
        super(Constantes.LINT_REG_016, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<VariableWrapper> variables = new LinkedHashSet<>();
        compilationUnit.accept(new VariableVisitor(), variables);
        for (VariableWrapper variableWrapper : variables) {
            if (!variableWrapper.isUsed()) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("Unused variable are removed");
                violation.setLine(variableWrapper.getLine());
                addViolation(violation);
            }
        }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}

