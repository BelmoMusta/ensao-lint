package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodBodyVisitor extends VoidVisitorAdapter<List<NameWrapper>> {
	
	@Override
	public void visit(MethodDeclaration n, List<NameWrapper> arg) {
		
		n.getBody().ifPresent(t -> {
			int start = t.getBegin().map(p -> p.line).orElse(-1);
			int end = t.getEnd().map(p -> p.line).orElse(-1);
			int bodyLength = end - start - 1; 
			
			if(bodyLength > 30 ) {
				arg.add(new NameWrapper(n.getNameAsString(), start)); 
			}
		});
		
		super.visit(n, arg);
	}

	
}
