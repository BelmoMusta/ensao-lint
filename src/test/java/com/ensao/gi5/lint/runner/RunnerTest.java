package com.ensao.gi5.lint.runner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunnerTest {

    @Test
    public void testMain() {
        Runner.main(new String[]{"-d", "testFiles"});
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
            Runner.main(new String[]{"-d", ""});
        });

    }

    @Test
    public void testMainNotAssignedDirectory() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Runner.main(new String[]{"-d"});
        });

    }
}