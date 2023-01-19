package com.ensao.gi5.lint.runner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunnerTest {

    public static final String ARG_PARAM = "-s";

    @Test
    public void testMain() {
        Runner.main(new String[]{ARG_PARAM, "testFiles"});
    }

    @Test
    public void testConstruct() {
      new Runner();
    }

    @Test
    public void testMainNullArgs() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Runner.main(null);
        });
    }

    @Test
    public void testMainEmptyArgs() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Runner.main(new String[0]);
        });
    }

    @Test
    public void testMainEmptyDirectory() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Runner.main(new String[]{ARG_PARAM, ""});
        });

    }

    @Test
    public void testMainNotAssignedDirectory() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Runner.main(new String[]{ARG_PARAM});
        });
    }
}