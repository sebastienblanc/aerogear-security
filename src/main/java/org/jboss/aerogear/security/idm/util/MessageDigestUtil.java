package org.jboss.aerogear.security.idm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {

    public static final String ALGORITHM_SHA = "SHA-256";
    public static final String CHARSET_NAME = "UTF-8";


    public static String createDigestPassword(String password) {

        MessageDigest messageDigest = null;
        byte[] digest = null;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM_SHA);
            digest = messageDigest.digest(password.getBytes(CHARSET_NAME));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return convertToHex(digest);
    }


    private static String convertToHex(byte[] digest) {
        StringBuilder hexBuffer = new StringBuilder();
        for (byte data : digest) {
            hexBuffer.append(String.format("%x", 0xff & data));
        }

        return hexBuffer.toString();
    }

}
