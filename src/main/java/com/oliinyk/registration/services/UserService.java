package com.oliinyk.registration.services;

import com.oliinyk.registration.dto.UserDto;
import com.oliinyk.registration.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> loginUser(String username, String password);

    Optional<User> registerUser(UserDto userDto);

    void deleteUser(String username);

    Optional<User> updateUser(User user, UserDto userDto);

}
