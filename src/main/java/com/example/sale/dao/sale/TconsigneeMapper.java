package com.example.sale.dao.sale;

import com.example.sale.model.Tconsignee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TconsigneeMapper {
    int insert(Tconsignee record);

    int insertSelective(Tconsignee record);

    List<Tconsignee> selectAll();

    List<Tconsignee> selectByKey(@Param("key") String key);

    int selectCountByKey(@Param("key") String key);
}