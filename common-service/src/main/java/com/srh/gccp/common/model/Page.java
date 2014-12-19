package com.srh.gccp.common.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by hokin.jim on 2014/12/19.
 */
public class Page<T> {

    private int pageNumber;
    private int pagesAvailable;
    private List<T> pageItems = Lists.newArrayList();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

}
