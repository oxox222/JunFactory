package com.example.sale.dao.sale;

import com.example.sale.model.Tproduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TproductMapper {

    int insert(Tproduct record);

    int insertSelective(Tproduct record);

    List<Tproduct> selectAll();

    List<Tproduct> selectByKey(@Param("party") String party, @Param("product") String product);

    int selectCountByKey(@Param("party") String party, @Param("product") String product);
}