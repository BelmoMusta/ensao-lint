package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.MethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodsWrapper;


public class MethodsRule extends Rule {
    public MethodsRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<MethodsWrapper> methods = new LinkedHashSet<>();
        compilationUnit.accept(new MethodsVisitor(), methods);
        if (methods.size() > 20) {
            MethodsWrapper methodsWrapper = methods.iterator().next();
            Violation violation = new Violation();
            violation.setFileName(compilationUnit.getFileName());
            violation.setDescription("The number of methods shouldn't exceed 20");
            violation.setLine(methodsWrapper.getLine());
            addViolation(violation);
        }
    }



    @Override
    public boolean isActive() {
        return true;
    }
}