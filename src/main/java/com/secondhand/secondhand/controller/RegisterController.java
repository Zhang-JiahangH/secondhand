package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.model.UserRole;
import com.secondhand.secondhand.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for register
 */
@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/user/register")
    public void addUser(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_USER);
    }

    @PostMapping("/admin/register")
    public void addAdmin(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_ADMIN);
    }

}

