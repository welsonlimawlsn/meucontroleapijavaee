package com.meucontrole.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public static String encrypt(String s) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] digest = algorithm.digest(s.getBytes());
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02X", 0xFF & b));
        }
        return hex.toString();
    }
}
