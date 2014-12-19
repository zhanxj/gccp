package com.srh.gccp.user.service;

import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.common.model.Page;
import com.srh.gccp.user.model.User;

import java.util.List;

/**
 * Created by hokin.jim on 2014/12/18.
 */
public interface UserService {

    public List<User> query() throws GccpException;

    public Page<User> queryPage(String name, int start, int size) throws GccpException;
}
