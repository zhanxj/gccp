package com.srh.gccp.common.dao;

import com.google.common.base.Strings;
import com.srh.gccp.common.exception.GccpException;
import com.srh.gccp.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hokin.jim on 2014/12/18.
 */

@Repository
public abstract class BaseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() throws GccpException {
        if (jdbcTemplate == null) {
            throw new GccpException("jdbcTemplate is null");
        } else {
            return jdbcTemplate;
        }
    }

    public <T> Page<T> fetchPage(
            Class clazz,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object[] args,
            final int pageNo,
            final int pageSize) throws GccpException {
        if (Strings.isNullOrEmpty(sqlCountRows) || Strings.isNullOrEmpty(sqlFetchRows) || pageNo < 1 || pageSize < 0) {
            throw new GccpException("分页查询参数不对");
        }
        // determine how many rows are availabl

        final int rowCount = jdbcTemplate.queryForObject(sqlCountRows, args, Integer.class);
        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }
        // create the page object
        final Page<T> page = new Page<T>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);

        // fetch a single page of results
        final int startRow = (pageNo - 1) * pageSize;
        StringBuffer sql = new StringBuffer();
        sql.append(sqlFetchRows).append(" limit ").append(startRow).append(" OFFSET ").append(pageSize);
        List<T> list = jdbcTemplate.query(sql.toString(), args, BeanPropertyRowMapper.newInstance(clazz));
        page.setPageItems(list);
        return page;
    }

    public <T> Page<T> fetchPageAndOrder(
            Class clazz,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object args[],
            final int pageNo,
            final int pageSize,
            final String order,
            final String orderby) throws GccpException {
        if (Strings.isNullOrEmpty(sqlCountRows) || Strings.isNullOrEmpty(sqlFetchRows) || pageNo < 1 || pageSize < 0 || Strings.isNullOrEmpty(order) || Strings.isNullOrEmpty(orderby)) {
            throw new GccpException("分页排序查询参数不对");
        }
        // determine how many rows are availabl
        final int rowCount = jdbcTemplate.queryForObject(sqlCountRows, args, Integer.class);
        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }

        // create the page object
        final Page<T> page = new Page<T>();
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);

        // fetch a single page of results
        final int startRow = (pageNo - 1) * pageSize;
        StringBuffer sql = new StringBuffer();
        sql.append(sqlFetchRows).append(" ORDER BY").append(orderby).append(" ").append(order).append(" limit ").append(startRow).append(" OFFSET ").append(pageSize);
        List<T> list = jdbcTemplate.query(sql.toString(), args, BeanPropertyRowMapper.newInstance(clazz));
        page.setPageItems(list);
        return page;
    }


}



