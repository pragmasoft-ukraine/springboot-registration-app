package com.oliinyk.registration.services.impl;

import com.oliinyk.registration.dto.UserDto;
import com.oliinyk.registration.model.CanadaUser;
import com.oliinyk.registration.model.State;
import com.oliinyk.registration.model.UsaUser;
import com.oliinyk.registration.model.User;
import com.oliinyk.registration.repositories.UserRepository;
import com.oliinyk.registration.services.PasswordService;
import com.oliinyk.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Optional<User> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordService.checkPassword(password, user.getPassword())) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> registerUser(UserDto userDto) {
        User user = getUserFromDto(userDto);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public Optional<User> updateUser(User user, UserDto userDto) {
        User updatedUser = getUpdatedUser(user, userDto);
        userRepository.save(updatedUser);
        return Optional.of(updatedUser);
    }

    private User getUpdatedUser(User user, UserDto userDto) {
        User updatedUser = getUserFromDto(userDto);
        updatedUser.setId(user.getId());
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            updatedUser.setPassword(user.getPassword());
        }
        return updatedUser;
    }


    private User getUserFromDto(UserDto userDto) {
        User user;
        if (userDto.getStateId() != null) {
            UsaUser usaUser = new UsaUser();
            State state = new State();
            state.setId(userDto.getStateId());
            usaUser.setState(state);
            user = usaUser;
        } else {
            CanadaUser canadaUser = new CanadaUser();
            canadaUser.setCity(userDto.getCity());
            canadaUser.setProvince(userDto.getProvince());
            user = canadaUser;
        }
        user.setUsername(userDto.getUsername());
        String hashedPassword = passwordService.hashPassword(userDto.getPassword());
        user.setPassword(hashedPassword);
        return user;
    }

}
