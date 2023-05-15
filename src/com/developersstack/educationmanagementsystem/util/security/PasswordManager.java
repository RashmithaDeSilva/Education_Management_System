package com.developersstack.educationmanagementsystem.util.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {

    public String encode(String rowPassword) {
        return BCrypt.hashpw(rowPassword, BCrypt.gensalt(10));
    }

    public boolean checkPassword(String rowPassword, String hashPassword) {
        return BCrypt.checkpw(rowPassword, hashPassword);
    }
}
