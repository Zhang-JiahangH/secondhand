package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.UserRole;
import com.secondhand.secondhand.service.UserDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller for user delete
 */
@RestController
public class UserDeleteController {
    UserDeleteService userDeleteService;

    @Autowired
    public UserDeleteController(UserDeleteService userDeleteService) {
        this.userDeleteService = userDeleteService;
    }

    @PostMapping(value = "/user/delete")
    public void deleteUser(Principal principal) {
        userDeleteService.delete(principal.getName(), UserRole.ROLE_USER);
    }
}

