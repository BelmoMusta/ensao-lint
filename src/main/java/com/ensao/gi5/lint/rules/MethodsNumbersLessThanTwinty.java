package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

/*
    - This class is used to implement "rule number 11" which guarantees that a class should not have more than 20 methods.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 11.
    - To implement the rule, we created the method "apply" which iterates over each type declaration in the specified file's compilation unit and checks if a
      class contains more than, 20 methods. If so, it creates a new instance of "Violation" with a description so that we can increment the number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

public class MethodsNumbersLessThanTwinty extends Rule{
    public MethodsNumbersLessThanTwinty() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    public void apply(CompilationUnitWrapper compilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            int methodCount = 0;
            for (BodyDeclaration<?> member : type.getMembers()) {
                if (member instanceof MethodDeclaration) {
                    methodCount++;
                }
            }
            if (methodCount > 20) {
                Violation vio = new Violation();
                vio.setDescription("Class " + type.getNameAsString() + " has more than 20 methods declared.");
                vio.setFileName(compilationUnit.getFileName());
                vio.setLine(type.getBegin().get().line);
                addViolation(vio);
            }
        }
    }




    @Override
    public boolean isActive() {
        return true;
    }
}
