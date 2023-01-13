package com.ensao.gi5.lint.wrapper;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.github.javaparser.ast.expr.SimpleName;

public class VariableWrapperTest {
    @Test
    public void testInit() {
        VariableWrapper variableWrapper = new VariableWrapper(new SimpleName("myVariable"), 1);
        VariableWrapper variableWrapper2 = new VariableWrapper(new SimpleName("myVariable"), 1);
        Assertions.assertEquals("myVariable", variableWrapper.getName());
        Assertions.assertEquals(1, variableWrapper.getLineNum());
        Assertions.assertEquals(variableWrapper, variableWrapper);
        Assertions.assertEquals(variableWrapper, variableWrapper2);
        Assertions.assertNotEquals(variableWrapper, null);
        Assertions.assertNotEquals(variableWrapper, "null");
    }

    @Test
    public void testStartsWithLowerCase() {
        VariableWrapper variableWrapper = new VariableWrapper(new SimpleName("myVariable"), 1);
        Assertions.assertTrue(variableWrapper.startsWithLowerCase("myVariable"));
        Assertions.assertFalse(variableWrapper.startsWithLowerCase("MyVariable"));
    }
}

