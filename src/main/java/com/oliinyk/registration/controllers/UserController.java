package com.oliinyk.registration.controllers;

import com.oliinyk.registration.dto.UserDto;
import com.oliinyk.registration.model.User;
import com.oliinyk.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String getUserPage() {

        return "userPage";
    }

    @DeleteMapping("/api/v1/user")
    public String deleteUser(@RequestParam String username, HttpSession session) {
        userService.deleteUser(username);
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/api/v1/user")
    public String updateUser(UserDto userDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User updatedUser = userService.updateUser(user, userDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"));
        session.setAttribute("user", updatedUser);
        return "redirect:/";
    }
}
