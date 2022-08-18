package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller for user search
 */
@RestController
public class UserSearchController {
    UserSearchService userSearchService;

    @Autowired
    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping(value = "/user/search/{username}")
    public User searchUser(@PathVariable String username) {
        return userSearchService.search(username);
    }

    @GetMapping(value = "/user/getinfo")
    public User getInfo(Principal principal) {
        return userSearchService.search(principal.getName());
    }
}
