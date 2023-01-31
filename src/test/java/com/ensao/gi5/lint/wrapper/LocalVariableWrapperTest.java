package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.resolution.types.ResolvedType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalVariableWrapperTest {
    @Test
    public void testInit() {
        LocalVariableWrapper variableDeclarator = new LocalVariableWrapper(new VariableDeclarator(new PrimitiveType(PrimitiveType.Primitive.INT),"myVariable"));
        LocalVariableWrapper variableDeclarator2 = new LocalVariableWrapper(new VariableDeclarator(new PrimitiveType(PrimitiveType.Primitive.INT),"myVariable"));
        Assertions.assertEquals(0, variableDeclarator.getLine());
        Assertions.assertEquals(variableDeclarator, variableDeclarator);
        Assertions.assertEquals(variableDeclarator, variableDeclarator2);
        Assertions.assertNotEquals(variableDeclarator, null);
        Assertions.assertNotEquals(variableDeclarator, "null");
    }
    }
