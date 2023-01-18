package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;


public class LocalVariableRule extends Rule {
    public LocalVariableRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<LocalVariableWrapper> LocalVariables = new LinkedHashSet<>();
        compilationUnit.accept(new LocalVariableVisitor(), LocalVariables);
        for (LocalVariableWrapper LocalVariableWrapper : LocalVariables){
            if(!LocalVariableWrapper.getName().matches("^[\t*a-z].*")){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("Local variable should start with lowercase");
                violation.setLine(LocalVariableWrapper.getLine());

                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}