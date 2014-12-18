package com.srh.gccp.user.controller;

import com.srh.gccp.user.model.User;
import com.srh.gccp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Hokin.jim on 2014/12/16.
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public List<User> getAllUsers() {
        return userService.query();
    }
}
