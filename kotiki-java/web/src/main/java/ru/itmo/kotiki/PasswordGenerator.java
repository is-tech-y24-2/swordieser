package ru.itmo.kotiki;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin";
        String encryptedPassword = encoder.encode(password);
        System.out.println(encryptedPassword);
        System.out.println("");
    }
}
