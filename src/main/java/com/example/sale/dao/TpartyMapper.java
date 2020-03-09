package com.example.sale.dao;

import com.example.sale.model.Tparty;

import java.util.List;

public interface TpartyMapper {
    int insert(Tparty record);

    int insertSelective(Tparty record);

    List<Tparty> selectAll();
}