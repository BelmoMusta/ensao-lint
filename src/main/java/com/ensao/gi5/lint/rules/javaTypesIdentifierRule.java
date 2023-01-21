package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypesWraper;

import java.util.Set;
import java.util.stream.Collectors;



public class javaTypesIdentifierRule extends Rule{

    public javaTypesIdentifierRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        
        final Set<TypesWraper> classesOrInterfaces = compilationUnit.getClassesOrInterfaces()
                .stream()
                .filter(typ -> !typ.getNameAsString().contains("_")) // elle ne doivent pas contenir "_"
                .filter(typ-> typ.getNameAsString().matches("[A-Z]\\w*")) // elle doit commencer parr une majuscule
                .map(TypesWraper::new)
                .collect(Collectors.toSet());
        
        final Set<TypesWraper> allTypes = compilationUnit.getClassesOrInterfaces()
                .stream().map(TypesWraper::new)
                .collect(Collectors.toSet());;
        
        for (TypesWraper declaredType : allTypes) {
            if (!classesOrInterfaces.contains(declaredType)) {
                final Violation violation = new Violation();
                violation.setDescription("inappropriate type identifier '" + declaredType + "'");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(declaredType.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
            return true;
    }
    
}
