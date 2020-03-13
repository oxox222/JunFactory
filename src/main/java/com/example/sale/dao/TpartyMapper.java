package com.example.sale.dao;

import com.example.sale.model.Tparty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpartyMapper {

    int insert(Tparty record);

    int insertSelective(Tparty record);

    List<Tparty> selectAll();

    List<Tparty> selectByKey(@Param("key") String key);

    int selectCountByKey(@Param("key") String key);
}