package com.example.functionality;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassHashing implements Serializable {
    public static String Hash(String pass){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed_bytes = md.digest(pass.getBytes());
            StringBuilder Hashed_Str = new StringBuilder();
            for (byte hash : hashed_bytes)
            {
                Hashed_Str.append(String.format("%02x", hash));
            }
            return Hashed_Str.toString();

        }
        catch(NoSuchAlgorithmException E)
        {
            E.printStackTrace();
            return "\0";
        }
    }
}
