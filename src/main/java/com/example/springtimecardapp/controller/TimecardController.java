package com.example.springtimecardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("timecard")
public class TimecardController {

    @GetMapping
    public String timecard() {
        return "timecard";
    }
}
