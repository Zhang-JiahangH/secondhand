package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.model.UserRole;
import com.secondhand.secondhand.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller for user update
 */
@RestController
public class UserUpdateController {

    UserUpdateService userUpdateService;

    @Autowired
    public UserUpdateController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping(value = "/user/update")
    public void updateUser(Principal principal, @RequestBody User user) {
        userUpdateService.update(principal.getName(), user, UserRole.ROLE_USER);
    }
}
