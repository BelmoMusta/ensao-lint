package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.Constructor;
import com.ensao.gi5.lint.wrapper.Method;

import java.util.ArrayList;
import java.util.List;

public class ClassRule extends Rule{

    //Le constructeur par défaut
    public ClassRule() {

        super(Constantes.LINT_REG_005, Level.HIGHEST);
    }

    //La redéfinition de la méthode apply
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<ClassWrapper> classesList = new ArrayList<>();
        compilationUnit.accept(new ClassVisitor(), classesList);


        for(ClassWrapper classWrapper : classesList) {

            //L'implémentation de la règle LINT_REG_011
            if(classWrapper.getMethods().size() > 20) {

                addViolation(ViolationFactory.createViolation(Constantes.LINT_REG_011, compilationUnit, classWrapper.getLine(), classWrapper.getName()));
            }

            //L'implémentation de la règle LINT_REG_012
            for(Constructor constructor : classWrapper.getConstructors()) {

                if(constructor.getParameters().size() > 2) {

                    addViolation(ViolationFactory.createViolation(Constantes.LINT_REG_012, compilationUnit, constructor.getLine(), constructor.getName()));
                }
            }

            for(Method method : classWrapper.getMethods()) {

                //Implémentation de la règle LINT_REG_008
                if(method.getLinesCounts() > 30) {

                    addViolation(ViolationFactory.createViolation(Constantes.LINT_REG_008, compilationUnit, method.getLine(), method.getName(), classWrapper.getName()));
                }

                //Implémentation de la règle LINT_REG_014
                if(method.getReturnCount() > 1 || method.getThrowCount() > 1) {

                    addViolation(ViolationFactory.createViolation(Constantes.LINT_REG_014, compilationUnit, method.getLine(), method.getName()));
                }

            }


        }



    }

    @Override
    public boolean isActive() {
        return true;
    }
}
