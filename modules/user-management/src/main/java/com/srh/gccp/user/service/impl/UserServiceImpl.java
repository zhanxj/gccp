package com.srh.gccp.user.service.impl;

import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.common.model.Page;
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
    public List<User> query() throws GccpException {
        return userDao.query();
    }

    @Override
    public Page<User> queryPage(String name, int start, int size) throws GccpException {
        return userDao.queryPage(name, start, size);
    }
}
