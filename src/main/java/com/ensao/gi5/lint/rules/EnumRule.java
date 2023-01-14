package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleSeptVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleSeptWrapper;
import java.util.LinkedHashSet;
import java.util.Set;


public class EnumRule extends Rule {

	
	 public EnumRule() {
	        super(Constantes.LINT_REG_007, Level.LOW);
	    }

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		 Set<RoleSeptWrapper> roleSeptWrappers = new LinkedHashSet<>();
	        compilationUnit.accept(new RoleSeptVisitor(), roleSeptWrappers);
	        for (RoleSeptWrapper roleSeptWrapper : roleSeptWrappers){
	            if (!roleSeptWrapper.getNom().matches(".*[A-Z].*")){
	                Violation violation = new Violation();
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(roleSeptWrapper.getLigne());
	                violation.setDescription("Le nom de l'enum " + roleSeptWrapper.getNom() + " ne respecte pas la convention de nommage");
	                addViolation(violation);
	            }
	        }
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

}
