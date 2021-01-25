package com.example.sale.dao.erp;


import com.example.sale.model.Custom;
import com.example.sale.model.CustomQueryVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CustomMapper extends BaseMapper<Custom> {

    List<Custom> queryList(@Param("query") CustomQueryVo query);
}