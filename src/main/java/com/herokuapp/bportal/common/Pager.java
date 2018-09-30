package com.herokuapp.bportal.common;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {
    private List<T> currentPageRecords;
    private Integer totalRecords;
    private Integer totalPages;
    private Integer pageSize;
    private Integer pageIndex;
    private List<Integer> listPageSizeOptions;
    private Integer startPage;
    private Integer endPage;
    private String sortName;
    private SortDirection sortDir;

    public Pager(List<Integer> listPageSizeOptions, Integer pageSize, Integer pageRange, Integer pageIndex, Integer totalRecords, List<T> currentPageRecords, String sortName, SortDirection sortDir) {
        this.listPageSizeOptions = listPageSizeOptions;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.currentPageRecords = currentPageRecords;
        this.totalPages = (totalRecords + pageSize - 1) / pageSize;
        this.sortName = sortName;
        if (sortDir == null) {
            this.sortDir = SortDirection.DESC;
        } else if (SortDirection.ASC.equals(sortDir)) {
            this.sortDir = SortDirection.ASC;
        } else {
            this.sortDir = SortDirection.DESC;
        }
        if (pageIndex <= 0) {
            this.pageIndex = 0;
        } else if (pageIndex >= totalPages - 1) {
            this.pageIndex = totalPages - 1;
        } else {
            this.pageIndex = pageIndex;
        }
        Integer overHalf = roundUp(pageRange, 2);
        startPage = this.pageIndex - overHalf + 1;
        endPage = this.pageIndex + overHalf -1;
        if (endPage >= totalPages - 1) {
            endPage = totalPages - 1;
            if (endPage <= 0) {
                endPage = 0;
            }
            startPage = endPage - pageRange + 1;
            if (startPage <= 0) {
                startPage = 0;
            }
        }
        if (startPage <= 0) {
            startPage = 0;
            endPage = startPage + pageRange - 1;
            if (endPage >= totalPages - 1) {
                endPage = totalPages - 1;
                if (endPage <= 0) {
                    endPage = 0;
                }
            }
        }
    }

    public Integer roundUp(Integer dividend, Integer divisor) {
        return (dividend + divisor - 1) / divisor;
    }

    public List<T> getCurrentPageRecords() {
        return currentPageRecords;
    }

    public void setCurrentPageRecords(List<T> currentPageRecords) {
        this.currentPageRecords = currentPageRecords;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<Integer> getListPageSizeOptions() {
        return listPageSizeOptions;
    }

    public void setListPageSizeOptions(List<Integer> listPageSizeOptions) {
        this.listPageSizeOptions = listPageSizeOptions;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public SortDirection getSortDir() {
        return sortDir;
    }

    public void setSortDir(SortDirection sortDir) {
        this.sortDir = sortDir;
    }

    public static void main(String[] args) {
        test(0, 0);
//        test(1, 0);
//        test(5, 0);
//        test(9, 2);
//        test(10, 0);
//        test(11, 0);
//        test(20, 0);
//        test(21, 0);
//        test(100, 7);
//        test(55, 0);
//        test(55, 2);
//        test(55, 3);
//        test(55, 4);
//        test(55, 8);
//        test(55, 10);
    }

    static void test(int totalRecords, int pageIndex ) {
        List<Integer> listPageSizeOptions = new ArrayList<>();
        listPageSizeOptions.add(5);
        listPageSizeOptions.add(10);
        listPageSizeOptions.add(15);
        listPageSizeOptions.add(20);
        List<String> currentPageRecords = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            currentPageRecords.add("Item " + i);
        }

        Pager<String> list = new Pager<>(listPageSizeOptions, 5, 5, pageIndex, totalRecords, currentPageRecords, null, null);
        System.out.println(JsonUtils.toJson(list));
    }

}
