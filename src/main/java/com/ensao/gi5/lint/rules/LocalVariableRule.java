package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.visitor.TypeNamingVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.ensao.gi5.lint.wrapper.TypeNamingWrapper;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class LocalVariableRule extends Rule{

    public LocalVariableRule(){
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<LocalVariableWrapper> localVariableWrapperSet = new LinkedHashSet<LocalVariableWrapper>();
        compilationUnit.accept(new LocalVariableVisitor(),localVariableWrapperSet);

        for(LocalVariableWrapper localVariableWrapper : localVariableWrapperSet){
            String name = localVariableWrapper.toString();
            if(name != name.toLowerCase()){
                final Violation violation = new Violation();
                violation.setDescription(localVariableWrapper.toString() +"n'est pas en miniscule'" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(localVariableWrapper.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
