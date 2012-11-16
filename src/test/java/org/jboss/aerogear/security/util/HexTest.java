/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
