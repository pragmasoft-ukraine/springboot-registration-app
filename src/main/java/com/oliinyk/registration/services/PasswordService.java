package com.oliinyk.registration.services;

public interface PasswordService {

    String hashPassword(String plainPassword);

    boolean checkPassword(String plainPassword, String hashedPassword);
}
