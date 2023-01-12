package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

public class LocalVariableVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private List<RuleWrapper> variables ;
    public LocalVariableVisitor(List<RuleWrapper> variables){
        this.variables=variables;
    }
    @Override
    public void visit(MethodDeclaration n, List<RuleWrapper> arg) {
        super.visit(n, arg);
        List<VariableDeclarator> localVariables = n.findAll(VariableDeclarator.class);
        for (VariableDeclarator localVariable : localVariables) {
            String locVar= localVariable.toString().split("=")[0] ;
            if (!Character.isLowerCase(locVar.charAt(0))) {
                variables.add(new RuleWrapper(locVar, localVariable.getBegin().get().line));
            }
        }
    }
}
