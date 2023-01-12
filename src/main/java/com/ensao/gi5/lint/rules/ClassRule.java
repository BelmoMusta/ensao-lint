package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.classe.ClassWrapper;
import com.ensao.gi5.lint.wrapper.classe.Construct;
import com.ensao.gi5.lint.wrapper.classe.Methode;

public class ClassRule extends Rule {

	public ClassRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {

		List<ClassWrapper> classes = new ArrayList<>();
		compilationUnit.accept(new ClassVisitor(), classes);

		for (ClassWrapper classe : classes) {
			if (classe.getMethodes().size() > 20) {
                final Violation violation = new Violation();
                violation.setDescription("The number of methods must not exceed 20 methods declared per class");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(classe.getLigne());
                violation.setRuleId(Constantes.LINT_REG_011);
                violation.setLevel(Level.HIGHEST);
                violation.setDescription(classe.getName() + " " + classe.getMethodes().size());
                addViolation(violation);
			}

			for (Construct construct : classe.getConstructs()) {
				if (construct.getParametres().size() > 2) {
					final Violation violation = new Violation();
	                violation.setDescription("The number of parameters of a constructor must not exceed 2");
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(classe.getLigne());
	                violation.setRuleId(Constantes.LINT_REG_012);
	                violation.setLevel(Level.HIGHEST);
	                violation.setDescription(construct.getName() + " " + construct.getParametres().size());
	                addViolation(violation);
				}
			}

			for (Methode methode : classe.getMethodes()) {
				if (methode.getLinesCount() > 30) {
					final Violation violation = new Violation();
	                violation.setDescription("The body of a method must not exceed 30 lines");
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(methode.getLigne());
	                violation.setRuleId(Constantes.LINT_REG_008);
	                violation.setLevel(Level.HIGHEST);
	                violation.setDescription(methode.getName() + " in " + classe.getName() + " " + methode.getLinesCount());
	                addViolation(violation);
				}
				if (methode.getParametres().size() > 2) {
					final Violation violation = new Violation();
	                violation.setDescription("The number of parameters of a method must not exceed 2");
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(classe.getLigne());
	                violation.setRuleId(Constantes.LINT_REG_012);
	                violation.setLevel(Level.HIGHEST);
	                violation.setDescription(methode.getName() + " " + methode.getParametres().size());
	                addViolation(violation);
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
