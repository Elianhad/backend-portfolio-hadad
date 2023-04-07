package com.portfolioehadad.portfolio.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderCustom {
    private final Argon2 argon2 = Argon2Factory.create();

    public String encode(String password){
       try {
           return argon2.hash(10, 65536,1,  password);
       }finally {
           argon2.wipeArray(password.getBytes());
       }
    }
    public boolean decode(String password, String passDB){
        return argon2.verify(password, passDB.getBytes());
    }
}
