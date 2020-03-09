package com.example.sale.dao;

import com.example.sale.model.Tsales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TsalesMapper {
    int insert(Tsales record);

    int insertSelective(Tsales record);

    List<Tsales> selectAll();

    List<Tsales> selectByKeyword(@Param("keyword") String keyword,
                                 @Param("index") int index, @Param("size") int size);

    Integer selectCountByKeyword(@Param("keyword") String keyword);

    List<Tsales> selectByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    double getTotalPrice(@Param("startTime") String startTime, @Param("endTime") String endTime,
                         @Param("party") String party, @Param("consignee") String consignee);
}