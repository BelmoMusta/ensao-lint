package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import java.util.Objects;

public class MethodeWrapper {
    
     private final String functionIdentifierFQN;
     private final String functionIdentifier;
     private final int line;
     
      public MethodeWrapper(MethodDeclaration methode) {
		this.functionIdentifierFQN = methode.getNameAsString();
		this.functionIdentifier = Utils.convertFQNToSimpleClassName(methode.getNameAsString());
                this.line = methode.getBegin().get().line;

	}
      
      public MethodeWrapper(String methodIdentifier) {
		this.functionIdentifierFQN = methodIdentifier;
		this.functionIdentifier = Utils.convertFQNToSimpleClassName(methodIdentifier);
                this.line = 0;
	}

        public MethodeWrapper(NameExpr name) {
		this(name.getNameAsString());
	}
	
	public MethodeWrapper(Name name) {
		this(name.asString());
	}
	public MethodeWrapper(SimpleName name) {
		this(name.asString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }

		if (!(o instanceof MethodeWrapper)) {
            return false;
        }
		MethodeWrapper that = (MethodeWrapper) o;
		return Objects.equals(functionIdentifier, that.functionIdentifier);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(functionIdentifier);
	}
	
	@Override
	public String toString() {
		return functionIdentifierFQN;
	}
        
         public int getLine() {
		return line;
	}
    
}
