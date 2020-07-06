package com.example.springtimecardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("timecard")
public class TimecardController {

    @GetMapping
    public String timecard() {
        return "timecard";
    }

    @PostMapping("/recorded")
    public String recorded(@RequestParam(value = "begin", required = false) String begin,
                           @RequestParam(value = "finish", required = false) String finish,
                           Model model) {
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("recordedTime", now);

        if (begin != null) {
            model.addAttribute("showBeginMsg", true);
            // TODO: DBに出社時刻を登録する
        } else if (finish != null) {
            model.addAttribute("showFinishMsg", true);
            // TODO: DBに退社時刻を登録する
        }

        return "recorded";
    }
}
