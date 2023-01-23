package com.ensao.gi5.lint.wrapper;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.github.javaparser.ast.expr.SimpleName;

public class ConstantWrapperTest {
    @Test
    public void testInit() {
        SimpleName varName = new SimpleName("MY_CONSTANT");
        ConstantWrapper constantWrapper = new ConstantWrapper(varName, 10);
        ConstantWrapper constantWrapper2 = new ConstantWrapper(varName, 10);
        Assertions.assertEquals("MY_CONSTANT", constantWrapper.getName());
        Assertions.assertEquals(10, constantWrapper.getLineNum());
        Assertions.assertEquals(constantWrapper, constantWrapper);
        Assertions.assertEquals(constantWrapper, constantWrapper2);
        Assertions.assertNotEquals(constantWrapper, null);
        Assertions.assertNotEquals(constantWrapper, "null");
    }
}