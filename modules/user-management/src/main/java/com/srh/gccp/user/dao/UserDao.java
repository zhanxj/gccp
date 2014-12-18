package com.srh.gccp.user.dao;

import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.user.model.User;

import java.util.List;

/**
 * Created by Hokin.jim on 2014/12/17.
 */
public interface UserDao {
    /**
     * 插入用户
     *
     * @param name
     * @param pwd
     */
    public void create(String name, String pwd) throws GccpException;

    /**
     * 根据ID查询用户
     *
     * @param id
     */
    public List<User> query(long id) throws GccpException;

    /**
     * 查询所有用户
     */
    public List<User> query() throws GccpException;

    /**
     * 分页查询用户
     */
    public List<User> pageQuery(String name, long current, long size) throws Exception;

}
