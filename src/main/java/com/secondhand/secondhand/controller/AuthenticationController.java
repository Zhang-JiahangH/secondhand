package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Token;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.model.UserRole;
import com.secondhand.secondhand.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for login
 */
@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/user/authenticate")
    public Token authenticateUser(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_USER);
    }

    @PostMapping("/admin/authenticate")
    public Token authenticateAdmin(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_ADMIN);
    }

}
