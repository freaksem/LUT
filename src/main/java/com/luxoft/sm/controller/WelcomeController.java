package com.luxoft.sm.controller;

import com.luxoft.sm.domain.User;
import com.luxoft.sm.services.CurrentUser;
import com.luxoft.sm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by smukhlaev on 22.12.2016.
 */
@Controller
public class WelcomeController {

    @Value("${amount.ruble}")
    private String userRubles;

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Iterable<User> users = userService.getAllUsers();
        model.put("users", users);

        String loggedUser = currentUser.getUsername();
        model.put("loggedUserName", loggedUser);

        model.put("message2", userRubles);
        return "welcome";
    }

}
