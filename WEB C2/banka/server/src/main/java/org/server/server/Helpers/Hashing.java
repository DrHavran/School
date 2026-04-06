package org.server.server.Helpers;

import java.security.MessageDigest;

public class Hashing {
    public static String hash(String input) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (Exception e){
            e.fillInStackTrace();
        }

        return null;
    }
}
