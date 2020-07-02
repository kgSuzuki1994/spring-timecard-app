package com.example.springtimecardapp.controller;

import com.example.springtimecardapp.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberReistrationController {

    private final AccountsService accountsService;

    @RequestMapping("/signup")
    public String signup(Model model) {

        model.addAttribute(new AccountsRegistrationRequest());

        return "sigunup";
    }

    @RequestMapping("/register")
    public String registerAccount(@ModelAttribute AccountsRegistrationRequest request) {

        // テーブルにinsertする
        accountsService.save(request.getUsername(), request.getPassword());
        return "result";
    }
}
