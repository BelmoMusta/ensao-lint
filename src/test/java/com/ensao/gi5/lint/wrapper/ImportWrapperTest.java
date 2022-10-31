package com.ensao.gi5.lint.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImportWrapperTest {
    @Test
    public void testInit() {
        ImportWrapper importWrapper = new ImportWrapper("com.example.test");
        ImportWrapper importWrapper2 = new ImportWrapper("com.example.test");
        Assertions.assertEquals(0, importWrapper.getLine());
        Assertions.assertEquals(importWrapper, importWrapper);
        Assertions.assertEquals(importWrapper, importWrapper2);
        Assertions.assertNotEquals(importWrapper, null);
        Assertions.assertNotEquals(importWrapper, "null");
    }
}
