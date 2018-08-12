package io.kang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static final String MD5 = "MD5";
    public static final String SHA256 = "SHA-256";

    public static String encrypt(String s, String messageDigest) {
        try {
            MessageDigest md = MessageDigest.getInstance(messageDigest);
            byte[] passBytes = s.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int k = 0; k < digested.length; k++) {
                sb.append(Integer.toHexString(0xff & digested[k]));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            return s;
        }
    }
}
