package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import java.util.Objects;

public class TypesWraper {
    
        private final String typeIdentifierFQN;
	private final String typeIdentifier;
        private final int line;
        
        public TypesWraper(TypeDeclaration classOrInterface) {
		this.typeIdentifierFQN = classOrInterface.getNameAsString();
		this.typeIdentifier = Utils.convertFQNToSimpleClassName(classOrInterface.getNameAsString());
                this.line = classOrInterface.asClassOrInterfaceDeclaration().getBegin().get().line;
	}
        
        public TypesWraper(String typeIdentifier) {
		this.typeIdentifierFQN = typeIdentifier;
		this.typeIdentifier = Utils.convertFQNToSimpleClassName(typeIdentifier);
                this.line = 0;
	}

        public TypesWraper(NameExpr name) {
		this(name.getNameAsString());
	}
	
	public TypesWraper(Name name) {
		this(name.asString());
	}
	public TypesWraper(SimpleName name) {
		this(name.asString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }

		if (!(o instanceof TypesWraper)) {
            return false;
        }
		TypesWraper that = (TypesWraper) o;
		return Objects.equals(typeIdentifier, that.typeIdentifier);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(typeIdentifier);
	}
	
	@Override
	public String toString() {
		return typeIdentifierFQN;
	}

        public int getLine() {
		return line;
	}
}
