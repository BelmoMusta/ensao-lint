package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.visitor.UnusedPrivateMethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class UnusedPrivateMethods extends Rule{

    public UnusedPrivateMethods(){
        super(Constantes.LINT_REG_017, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();
        UnusedPrivateMethodVisitor visitor;
        for(MethodDeclaration method : methodDeclarations){
//            visitor.setMethod(method);
//            visitor.visit(compilationUnit,null);
//            if(method.isPrivate() && !visitor.isUsed()){
//
//            }
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
