package com.itransition.task_5.controller;

import com.itransition.task_5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiUserController {

    private final UserService userService;

    @GetMapping("users/get-user/{key}")
    public List<String> getUsers(
            @PathVariable String key
    ) {
        return userService.getUsernames(key);
    }

}
