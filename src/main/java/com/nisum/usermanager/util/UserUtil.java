package com.nisum.usermanager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UserUtil {
    private UserUtil(){}

    public static String encrypt(String password){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String createJwt() throws IllegalArgumentException, UnsupportedEncodingException {
        Algorithm al = Algorithm.HMAC256("secretkey");
        String token = JWT.create()
                .withIssuer ("Emisor")
                .withSubject ("Usuario")
                .withClaim("userid", 1234)
                .withExpiresAt(new Date(System.currentTimeMillis()+360000))
                .sign(al);

        return token;
    }
}
