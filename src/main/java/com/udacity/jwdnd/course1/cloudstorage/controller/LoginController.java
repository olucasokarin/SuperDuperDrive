package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/login")
public class LoginController {

    AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute Authentication authentication, Model model) {
        String loginError = null;

        try {
            Authentication authenticated = authenticationService.authenticate(authentication);
            if(authenticated == null)
                loginError = "Invalid username or password";

        }
        catch (AuthenticationException message){
            loginError = "Invalid username or password";
        }

        if (loginError == null)
            model.addAttribute("loginSuccess", true);
        else
            model.addAttribute("loginError", loginError);

        return "login";
    }
}
