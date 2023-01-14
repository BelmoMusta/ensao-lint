package com.ensao.gi5.lint.wrapper;


import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.Modifier;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class TypeNamingWrapperTest {

    @Test
    public void testInit() {


        TypeNamingWrapper typeName = new TypeNamingWrapper(new SimpleName("MyClass"));
        TypeNamingWrapper typeName2 = new TypeNamingWrapper(new SimpleName("MyClass"));
        Assertions.assertEquals(0, typeName.getLine());
        Assertions.assertEquals(typeName, typeName);
        Assertions.assertEquals(typeName, typeName2);
        Assertions.assertNotEquals(typeName, null);
        Assertions.assertNotEquals(typeName, "null");
    }
}
