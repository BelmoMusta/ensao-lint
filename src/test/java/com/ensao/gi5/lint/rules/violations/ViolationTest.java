package com.ensao.gi5.lint.rules.violations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViolationTest {

    public static final String FILE_NAME_JAVA = "FileName.java";

    @Test
    public void testSetFileName() {
        Violation violation = new Violation();
        violation.setFileName(FILE_NAME_JAVA);
        assertEquals(FILE_NAME_JAVA, violation.getFileName());
    }

    @Test
    public void testSetLine() {
        Violation violation = new Violation();
        violation.setLine(48);
        assertEquals(48, violation.getLine());
    }


    @Test
    public void testSetDescription() {
        Violation violation = new Violation();
        violation.setDescription("this is a description");
        assertEquals("this is a description", violation.getDescription());
    }

    @Test
    public void testSetRuleId() {
        Violation violation = new Violation();
        violation.setRuleId("RULE00");
        assertEquals("RULE00", violation.getRuleId());

    }

    @Test
    public void testTestToString() {
    }

    @Test
    public void testCompareTo() {
        Violation a = new Violation();
        Violation b = new Violation();
        assertEquals(0, a.compareTo(b));

    }

    @Test
    public void testEquals() {
        Violation a = new Violation();
        Violation b = new Violation();
        assertEquals(a, b);
        assertEquals(a, a);
        assertNotEquals(a, null);
        assertNotEquals(a, "violation");

        Violation c = new Violation();
        c.setLine(20);
        assertNotEquals(a, c);

        Violation d = new Violation();
        d.setLine(20);
        d.setRuleId("RULE00");

        assertNotEquals(d, c);

        c.setRuleId(d.getRuleId());
        c.setFileName("test.txt");

        assertNotEquals(d, c);
    }

    @Test
    public void testTestHashCode() {
        Violation a = new Violation();
        a.setLine(10);
        Violation b = new Violation();
        b.setLine(10);

        assertEquals(a.hashCode(), b.hashCode());

    }

    @Test
    public void testSetLevel() {
    }

    @Test
    public void testGetLevel() {
    }
}