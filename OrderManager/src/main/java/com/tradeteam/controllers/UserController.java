package com.tradeteam.controllers;

import com.tradeteam.security.OrderManagerUserDetails;
import com.tradeteam.security.OrderManagerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tradeteam.services.UserService;
import com.tradeteam.entities.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderManagerUserDetailsService userDetailsService;
    //Mapping methods

    @GetMapping("/")
    public String index(@AuthenticationPrincipal OrderManagerUserDetails userDetails) {
        if(userDetails != null) {
            return "redirect:/orders";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String reg() {
        return "register_form"; //name of file to get
    }

    @PostMapping("/register")
    public String registerNewUser(
            @RequestParam("userName") String userName,
            @RequestParam("userPassword") String userPassword,
            @RequestParam("userEmail") String userEmail) {
        User u1 = new User(userName, userPassword, userEmail);
        User savedInfo = userDetailsService.saveUser(u1);
        if (savedInfo != null) {
            return "redirect:/login";
        } else {
            return "failure";
        }
    }
}
