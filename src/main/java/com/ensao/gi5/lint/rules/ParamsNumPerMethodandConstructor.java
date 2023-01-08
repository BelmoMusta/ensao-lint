package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class ParamsNumPerMethodandConstructor extends Rule {

    private int maxParams = 2;

    public ParamsNumPerMethodandConstructor() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }


    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();
        List<ConstructorDeclaration> constructorDeclarations = compilationUnit.getConstructors();

        for (ConstructorDeclaration cd : constructorDeclarations) {
            if (cd.getParameters().size() > this.maxParams) {
                Violation violation = new Violation();
                violation.setDescription("Max number of constructor '" + cd.getNameAsString() + "' parameters is 2 ");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(cd.getBody().getBegin().get().line);
                addViolation(violation);
            }
        }

        for (MethodDeclaration md : methodDeclarations) {
            if (md.getParameters().size() > this.maxParams) {
                Violation violation = new Violation();
                violation.setDescription("Max number of method '" + md.getNameAsString() + "' parameters is 2 ");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(md.getBody().get().getBegin().get().line);
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
