package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.ClassWrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.ClassWrapper.Constructor;
import com.ensao.gi5.lint.wrapper.ClassWrapper.Method;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class ClassRule extends Rule{
    public ClassRule() {
        super(Constantes.LINT_REG_005, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {
        List<ClassWrapper> classes = new ArrayList<>();
        cu.accept(new ClassVisitor(), classes);


        for(ClassWrapper cls : classes){
            if(cls.getMethods().size()>20){
                addViolation(ViolationFactory.create(Constantes.LINT_REG_011, cu, cls.getLine(), cls.getName()));
            }

            for(Constructor constructor : cls.getConstructors()){
                if(constructor.getParameters().size()>2)
                    addViolation(ViolationFactory.create(Constantes.LINT_REG_012, cu, constructor.getLine(), constructor.getName()));
            }

            for(Method method : cls.getMethods()){
                if(method.getLinesCount()>30) {
                    addViolation(ViolationFactory.create(Constantes.LINT_REG_008, cu, method.getLine(),
                            method.getName(), cls.getName()));
                }
                if(method.getParameters().size()>2) {
                    addViolation(ViolationFactory.create(Constantes.LINT_REG_012, cu, method.getLine(), method.getName()));
                }
                if(method.getReturnCount()>1) {
                    addViolation(ViolationFactory.create(Constantes.LINT_REG_014, cu, method.getLine(), method.getName()));
                }
                if(method.getThrowCount()>1) {
                    addViolation(ViolationFactory.create(Constantes.LINT_REG_014, cu, method.getLine(), method.getName()));
                }
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
