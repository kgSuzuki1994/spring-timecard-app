package com.example.springtimecardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimecardController {

    @GetMapping("timecard")
    public String timecard() {
        return "timecard";
    }
}
