package com.luxoft.sm.controller;

import com.luxoft.sm.services.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by smukhlaev on 22.12.2016.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Map<String, Object> model, Authentication authentication) {
        if(authentication != null) {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

            String loggedUser = currentUser.getUsername();
            model.put("loggedUserName", loggedUser);
        }
        else model.put("loggedUserName", null);

        return "home";
    }

}
