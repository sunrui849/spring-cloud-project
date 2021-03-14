package com.sr.cloud.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private List<T> list;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总页数
    private int totalPages;
    //总记录数
    private long total;
}
