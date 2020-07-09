package com.example.springtimecardapp.controller;

import com.example.springtimecardapp.service.RecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("timecard")
@RequiredArgsConstructor
public class TimecardController {

    private final RecordsService recordsService;

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

        // Principalからログインユーザの情報を取得
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        model.addAttribute("userName", userName);

        if (begin != null) {
            model.addAttribute("showBeginMsg", true);
            recordsService.insertBeginTime(userName, now);

        } else if (finish != null) {
            model.addAttribute("showFinishMsg", true);
            // TODO: DBに退社時刻を登録する
        }

        return "recorded";
    }
}
