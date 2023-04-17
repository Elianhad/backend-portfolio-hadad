package com.portfolioehadad.portfolio.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class PasswordEncoderCustom {
    private final Argon2 argon2 = Argon2Factory.create();

    public String encode(String password){
        try {
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            return argon2.hash(10, 65536, 1, passwordBytes);
        } finally {
            argon2.wipeArray(password.getBytes());
        }
    }

    public boolean decode(String password, String passDB){
        return argon2.verify(passDB, password.getBytes(StandardCharsets.UTF_8));
    }
}
