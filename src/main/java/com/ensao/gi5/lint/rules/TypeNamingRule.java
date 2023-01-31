package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.TypeNamingVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypeNamingWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class TypeNamingRule extends Rule{

    public TypeNamingRule(){
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<TypeNamingWrapper> typeNamingWrapperSet = new LinkedHashSet<TypeNamingWrapper>();
        compilationUnit.accept(new TypeNamingVisitor(),typeNamingWrapperSet);

        for(TypeNamingWrapper namingWrapper : typeNamingWrapperSet){
            String name = namingWrapper.toString();
            if(!Character.isUpperCase(name.charAt(0)) || name.contains("_")){
                final Violation violation = new Violation();
                violation.setDescription(namingWrapper.toString()+"ne commence pas en majuscule ou contient '_'" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(namingWrapper.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
