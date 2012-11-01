package org.jboss.aerogear.security.util;

public class Hex {

    public static String toString(byte[] bytes) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < bytes.length; i++)
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
        if ('a' <= character && character <= 'f') { return character - 'a' + 10; }
        if ('A' <= character && character <= 'F') { return character - 'A' + 10; }
        if ('0' <= character && character <= '9') { return character - '0'; }
        throw new IllegalArgumentException(String.valueOf(character));
    }
}
