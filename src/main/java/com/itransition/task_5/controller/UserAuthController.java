package com.itransition.task_5.controller;

import com.itransition.task_5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller

@RequestMapping("/login")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @GetMapping
    public String loginPage(){
        return "login";
    }

    @PostMapping
    public String login(
            @RequestParam String username,
            HttpSession session
    ){
        userService.saveUser(username);
        session.setAttribute("username", username);
        return "redirect:/";
    }


}
