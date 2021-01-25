package com.example.sale.dao.sale;

import com.example.sale.model.TsalesProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsalesProductMapper {
    int insert(TsalesProduct record);

    int insertSelective(TsalesProduct record);

    List<TsalesProduct> selectById(@Param("id") String id);

    List<TsalesProduct> selectByProductAndId(@Param("id") String id, @Param("product") String product);
}