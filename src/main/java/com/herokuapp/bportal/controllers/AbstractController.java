package com.herokuapp.bportal.controllers;

import com.herokuapp.bportal.common.Pager;

import java.util.Arrays;
import java.util.List;

public class AbstractController {

    protected static final Integer DEFAULT_PAGE_SIZE = 10;
    protected static final List<Integer> LIST_PAGE_SIZE_OPTIONS = Arrays.asList(5, 10, 20, 50);

    protected <T> Pager<T> manualBuildPager(List<T> listOfRecords, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        int from = Math.max(0, pageIndex * pageSize);
        int to = Math.min(listOfRecords.size(), (pageIndex + 1) * pageSize);
        List<T> currentPageRecords = listOfRecords.subList(from, to);
        Pager<T> pager = new Pager<>(LIST_PAGE_SIZE_OPTIONS, pageSize, 5, pageIndex, listOfRecords.size(), currentPageRecords, null, null);
        return pager;
    }
}
