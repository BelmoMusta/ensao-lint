package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ruleDeuzeWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;
public class ruleDeuzeVisitor  extends VoidVisitorAdapter<Set<ruleDeuzeWrapper>> {
	 @Override
	    public void visit(MethodDeclaration methodDeclaration, Set<ruleDeuzeWrapper> arg ){
	       arg.add(new ruleDeuzeWrapper(methodDeclaration.getParameters().size(), methodDeclaration.getBegin().get().line));
	       super.visit(methodDeclaration, arg);
	    }
	    @Override
	    public void visit(ConstructorDeclaration constructorDeclaration, Set<ruleDeuzeWrapper> arg ){
	        arg.add(new ruleDeuzeWrapper(constructorDeclaration.getParameters().size(), constructorDeclaration.getBegin().get().line));
	        super.visit(constructorDeclaration, arg);
	    }

}
