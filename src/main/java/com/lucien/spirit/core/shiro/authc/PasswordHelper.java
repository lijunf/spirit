package com.lucien.spirit.core.shiro.authc;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha512Hash;

import com.lucien.spirit.module.security.model.User;

public class PasswordHelper {
    public static User generatePassword(User user) {
        byte[] passwordSalt = UUID.randomUUID().toString().getBytes();
        user.setPasswordSalt(passwordSalt);
        String passwordHash = new Sha512Hash(user.getPassword(), user.getName() + new String(passwordSalt), 99)
                .toString();
        user.setPasswordHash(passwordHash);
        return user;
    }
}
