package com.srh.gccp.user.controller;

import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.common.model.Page;
import com.srh.gccp.user.model.User;
import com.srh.gccp.user.service.UserService;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hokin.jim on 2014/12/16.
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public Object getAllUsers(@RequestParam(value = "jsoncallback", required = false) String jsoncallback) throws GccpException {
        if (jsoncallback != null) {
            return new JSONPObject(jsoncallback, userService.query());
        }
        return userService.query();
    }

    @RequestMapping("/{name}/{start}/{size}")
    public Page<User> getAllUsers(@PathVariable String name, @PathVariable int start, @PathVariable int size) throws GccpException {
        return userService.queryPage(name, start, size);
    }
}
