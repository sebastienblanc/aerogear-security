package org.jboss.aerogear.security.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HexTest {

    @Test
    public void testToString() throws Exception {
        byte[] hexString =  {(byte) 0xFF, (byte) 0xD0, (byte) 0xFF, (byte) 0xD1};
        assertEquals("ffd0ffd1", Hex.toString(hexString));
    }

    @Test
    public void testToAscii() throws Exception {
        assertEquals("AeroGear", Hex.toAscii("4165726f47656172"));
    }

    @Test
    public void testToInt() throws Exception {
        assertEquals(10, Hex.toInt('A'));
    }
}
