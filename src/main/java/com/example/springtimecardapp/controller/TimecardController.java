package com.example.springtimecardapp.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class TimecardController {

    @GetMapping(path = "login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, HttpSession session) {

        model.addAttribute("showErrorMsg", false);
        model.addAttribute("showLogoutedMsg", false);

        if (error != null) {
            if (session != null) {
                AuthenticationException exception =
                        (AuthenticationException) session
                                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (exception != null) {
                    model.addAttribute("showErrorMsg", true);
                    model.addAttribute("errorMsg", exception.getMessage());
                }
            }
        } else if (logout != null) {
            model.addAttribute("showLogoutedMsg", true);
        }
        return "login";
    }

    @GetMapping("success")
    public String success() {
        return "success";
    }
}