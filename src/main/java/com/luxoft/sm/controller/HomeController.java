package com.luxoft.sm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smukhlaev on 22.12.2016.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Authentication authentication) {
//        if(authentication != null) {
//            return "redirect:user";
//        }
//        else {
//            return "redirect:login";
//        }
        return "index";
    }

}
