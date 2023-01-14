package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassAttributeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ClassAttributeWrapper;


public class ClassAttributeRule extends Rule {
    public ClassAttributeRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<ClassAttributeWrapper> ClassAttributes = new LinkedHashSet<>();
        compilationUnit.accept(new ClassAttributeVisitor(), ClassAttributes);
        for (ClassAttributeWrapper ClassAttributeWrapper : ClassAttributes){
            if (!ClassAttributeWrapper.getName().matches(".*[a-z].*")) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("Class attributes should start with lowercase");
                violation.setLine(ClassAttributeWrapper.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}