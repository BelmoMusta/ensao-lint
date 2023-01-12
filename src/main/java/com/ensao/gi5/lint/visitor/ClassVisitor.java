package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.classe.ClassWrapper;
import com.ensao.gi5.lint.wrapper.classe.Construct;
import com.ensao.gi5.lint.wrapper.classe.Methode;
import com.ensao.gi5.lint.wrapper.classe.Parametre;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<List<ClassWrapper>> {

	@Override
	public void visit(ClassOrInterfaceDeclaration CID, List<ClassWrapper> arg) {
		ClassWrapper classWrapper = new ClassWrapper(CID.getNameAsString(), Utils.getLine(CID.getName()));

		CID.getConstructors().forEach(c -> {
			Construct construct = new Construct(c.getNameAsString(), Utils.getLine(c.getName()));
			c.getParameters().forEach(param -> {
				construct.getParametres().add(new Parametre(param.getNameAsString(), param.getTypeAsString()));
			});
			classWrapper.getConstructs().add(construct);
		});

		CID.getMethods().forEach(m -> {
			Methode methode = new Methode(m.getNameAsString(), m.getTypeAsString(), m.getAccessSpecifier().asString(),
					m.getBegin().map(p -> p.line).orElse(-1));

			m.getParameters()
					.forEach(p -> methode.getParametres().add(new Parametre(p.getNameAsString(), p.getTypeAsString())));
			classWrapper.getMethodes().add(methode);
		});
		
		arg.add(classWrapper);
		super.visit(CID, arg);
	}
}
