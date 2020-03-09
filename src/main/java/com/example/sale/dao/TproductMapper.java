package com.example.sale.dao;

import com.example.sale.model.Tproduct;

import java.util.List;

public interface TproductMapper {
    int insert(Tproduct record);

    int insertSelective(Tproduct record);

    List<Tproduct> selectAll();
}