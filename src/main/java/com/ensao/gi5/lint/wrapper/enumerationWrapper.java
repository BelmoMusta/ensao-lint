package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import java.util.Objects;


public class enumerationWrapper {
    
    private final String enumerationIdentifierFQN;
     private final String enumerationIdentifier;
     private final int line;
     
      public enumerationWrapper(EnumDeclaration enume) {
		this.enumerationIdentifierFQN = enume.getNameAsString();
		this.enumerationIdentifier = Utils.convertFQNToSimpleClassName(enume.getNameAsString());
                this.line = enume.getBegin().get().line;

	}
      
      public enumerationWrapper(String methodIdentifier) {
		this.enumerationIdentifierFQN = methodIdentifier;
		this.enumerationIdentifier = Utils.convertFQNToSimpleClassName(methodIdentifier);
                this.line = 0;
	}

        public enumerationWrapper(NameExpr name) {
		this(name.getNameAsString());
	}
	
	public enumerationWrapper(Name name) {
		this(name.asString());
	}
	public enumerationWrapper(SimpleName name) {
		this(name.asString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }

		if (!(o instanceof enumerationWrapper)) {
            return false;
        }
		enumerationWrapper that = (enumerationWrapper) o;
		return Objects.equals(enumerationIdentifier, that.enumerationIdentifier);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(enumerationIdentifier);
	}
	
	@Override
	public String toString() {
		return enumerationIdentifierFQN;
	}
        
         public int getLine() {
		return line;
	}
    
}
