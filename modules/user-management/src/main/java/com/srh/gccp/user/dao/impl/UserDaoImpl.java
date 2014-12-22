package com.srh.gccp.user.dao.impl;

import com.srh.gccp.common.dao.BaseDao;
import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.common.model.Page;
import com.srh.gccp.user.dao.UserDao;
import com.srh.gccp.user.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hokin.jim on 2014/12/17.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public void create(String name, String pwd) throws GccpException {
        getJdbcTemplate().update("INSERT INTO t_user( name, pwd) VALUES ( ?,?)", name, pwd);
    }

    @Override
    public List<User> query(long id) throws GccpException {
        return getJdbcTemplate().query("SELECT * FROM t_user WHERE id=?", BeanPropertyRowMapper.newInstance(User.class), id);
    }

    @Override
    public List<User> query() throws GccpException {
        return getJdbcTemplate().query("SELECT * FROM t_user", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Page<User> queryPage(String name, int start, int size) throws GccpException {
        Object[] args = {name};
        return fetchPage(User.class, "SELECT COUNT (id) FROM t_user WHERE name=?", "SELECT * FROM t_user WHERE name=?", args, start, size);
    }
}
