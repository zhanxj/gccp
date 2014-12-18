package com.srh.gccp.common.dao;

import com.google.common.collect.Lists;
import com.srh.gccp.common.exception.GccpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hokin.jim on 2014/12/18.
 */

@Repository
public abstract class BaseDao {

    public final static int NUM_PER_PAGE = 20; // 默认每页行数

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() throws GccpException {
        if (jdbcTemplate == null) {
            throw new GccpException("jdbcTemplate is null");
        } else {
            return jdbcTemplate;
        }
    }

    /**
     * 分页查询
     *
     * @param sql
     * @param currentPage
     * @param size
     * @param rowMapper
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> pageQuery(String sql, long currentPage, long size, RowMapper<T> rowMapper, Object... args) throws Exception {
        List list = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        long start = (currentPage - 1) * size;
        stringBuilder.append(sql).append(" limit ").append(start).append(',').append(size);
        return getJdbcTemplate().query(stringBuilder.toString(), rowMapper, args);
    }

    /**
     * 升序查询
     *
     * @param sql
     * @param sort
     * @param rowMapper
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> sortQueryAsc(String sql, String sort, RowMapper<T> rowMapper, Object... args) throws Exception {
        List list = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql).append("  ").append("order by ").append(sort).append(" ASC");
        return getJdbcTemplate().query(stringBuilder.toString(), rowMapper, args);
    }

    /**
     * 降序查询
     *
     * @param sql
     * @param sort
     * @param rowMapper
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> sortQueryDesc(String sql, String sort, RowMapper<T> rowMapper, Object... args) throws Exception {
        List list = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql).append("  ").append("order by ").append(sort).append(" DESC");
        return getJdbcTemplate().query(stringBuilder.toString(), rowMapper, args);
    }


}
