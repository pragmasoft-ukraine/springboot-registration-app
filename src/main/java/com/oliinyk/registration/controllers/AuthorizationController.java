package com.oliinyk.registration.controllers;

import com.oliinyk.registration.dto.UserDto;
import com.oliinyk.registration.model.State;
import com.oliinyk.registration.model.User;
import com.oliinyk.registration.services.UsaStateService;
import com.oliinyk.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthorizationController {

    private UsaStateService usaStateService;
    private UserService userService;

    @Autowired
    public AuthorizationController(@Qualifier("userService") UserService userService,
                          @Qualifier("usaStateService") UsaStateService usaStateService) {
        this.userService = userService;
        this.usaStateService = usaStateService;
    }

    @PostMapping("/api/v1/signin")
    public String signIn(@RequestParam String username,
                         @RequestParam String password,
                         HttpSession session) {
        User user = userService.loginUser(username, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("/api/v1/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/api/v1/signup")
    public String signUp(UserDto userDto, HttpSession session) {
        User user = userService.registerUser(userDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"));
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public ModelAndView getSignUpPage() {
        List<State> states = usaStateService.getStates();
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("states", states);
        return modelAndView;
    }

}
