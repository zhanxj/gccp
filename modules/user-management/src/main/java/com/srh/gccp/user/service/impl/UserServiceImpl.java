package com.srh.gccp.user.service.impl;

import com.srh.gccp.user.dao.UserDao;
import com.srh.gccp.user.model.User;
import com.srh.gccp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hokin.jim on 2014/12/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> query() {
        return userDao.query();
    }
}
