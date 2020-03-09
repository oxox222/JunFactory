package com.example.sale.model;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Description: TODO
 * @Author: PANLVZ
 * @Date: 2020-03-05
 */
public class PageResult<T> {

    private List<T> list;

    private Integer count;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
