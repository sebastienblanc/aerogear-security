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

public class Hex {

    public static String toString(byte[] bytes) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
            str.append(String.format("%x", bytes[i]));
        return str.toString();
    }

    public static String toAscii(String hex) {
        int n = hex.length();
        StringBuilder sb = new StringBuilder(n / 2);
        for (int i = 0; i < n; i += 2) {
            char a = hex.charAt(i);
            char b = hex.charAt(i + 1);
            sb.append((char) ((toInt(a) << 4) | toInt(b)));
        }
        return sb.toString();
    }

    public static int toInt(char character) {
        if ('a' <= character && character <= 'f') {
            return character - 'a' + 10;
        }
        if ('A' <= character && character <= 'F') {
            return character - 'A' + 10;
        }
        if ('0' <= character && character <= '9') {
            return character - '0';
        }
        throw new IllegalArgumentException(String.valueOf(character));
    }
}
