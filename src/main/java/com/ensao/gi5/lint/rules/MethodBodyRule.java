package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodBodyRule extends Rule{

    public MethodBodyRule(){
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();

        for (MethodDeclaration method : methodDeclarations) {
            int size = compilationUnit.getSizeOfMethod(method);

            if (size > 30 ){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLevel(Level.HIGHEST);
                violation.setLine(method.getBegin().get().line);
                violation.setDescription(method.getName() + "must not be in the total of more than 30 lines");
            }
        }

    }
    @Override
    public boolean isActive() {
        return false;
    }
}
