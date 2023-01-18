package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class UtilsTest {
    @Test
    public void testConvertFQNToSimpleClassName() {
        Utils.convertFQNToSimpleClassName(null);
        Utils.convertFQNToSimpleClassName("io.musta");
        Utils.convertFQNToSimpleClassName("musta");
    }

    @Test
    public void testGetCompilationUnit() {
        Utils.getCompilationUnit(new File("testFiles/normalExecution/Example.java"));
    }

    @Test
    public void testGetFilesFromDirectoryAsString(){
        Utils.getFilesFromDirectory("testFiles");
    }

    @Test
    public void testGetFilesFromDirectory() {
        Utils.getFilesFromDirectory(new File("testFiles"));
    }

    @Test
    public void testFileNotFound() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Utils.getCompilationUnit(new File(""));
        });
    }

   @Test
    public  void testNoCompilationUnitFound() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
           new Utils();
        });
    }


}