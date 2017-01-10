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
 * Created by sm on 10.01.2017.
 */
@Controller
public class UserController {

    @Value("${amount.ruble}")
    private String userRubles;

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String welcome(Map<String, Object> model, Authentication authentication) {

        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        if(currentUser != null) {
            String loggedUser = currentUser.getUsername();
            model.put("loggedUserName", loggedUser);
        }


        Iterable<User> users = userService.getAllUsers();
        model.put("users", users);

        model.put("message2", userRubles);

        return "user";
    }

}