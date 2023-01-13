package com.ensao.gi5.lint.wrapper;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.github.javaparser.ast.expr.SimpleName;

public class NamingWrapperTest {
    @Test
    public void testInit() {
        SimpleName name = new SimpleName("testName");
        NamingWrapper namingWrapper = new NamingWrapper(name, 1);
        NamingWrapper namingWrapper2 = new NamingWrapper(name, 1);
        Assertions.assertEquals(1, namingWrapper.getLine());
        Assertions.assertEquals("testName", namingWrapper.getName());
        Assertions.assertEquals(namingWrapper, namingWrapper);
        Assertions.assertEquals(namingWrapper, namingWrapper2);
        Assertions.assertNotEquals(namingWrapper, null);
        Assertions.assertNotEquals(namingWrapper, "null");
    }

    @Test
    public void testStartsWithUpperCase() {
        SimpleName name = new SimpleName("TestName");
        NamingWrapper namingWrapper = new NamingWrapper(name, 1);
        Assertions.assertTrue(namingWrapper.startsWithUpperCase(namingWrapper.getName()));
    }

    @Test
    public void testDoesNotStartWithUpperCase() {
        SimpleName name = new SimpleName("testName");
        NamingWrapper namingWrapper = new NamingWrapper(name, 1);
        Assertions.assertFalse(namingWrapper.startsWithUpperCase(namingWrapper.getName()));
    }

    @Test
    public void testContainsUnderscore() {
        SimpleName name = new SimpleName("test_name");
        NamingWrapper namingWrapper = new NamingWrapper(name, 1);
        Assertions.assertTrue(namingWrapper.containsUnderscore(namingWrapper.getName()));
    }

    @Test
    public void testDoesNotContainUnderscore() {
        SimpleName name = new SimpleName("testName");
        NamingWrapper namingWrapper = new NamingWrapper(name, 1);
        Assertions.assertFalse(namingWrapper.containsUnderscore(namingWrapper.getName()));
    }
}
