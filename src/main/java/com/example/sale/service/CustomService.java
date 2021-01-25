package com.example.sale.service;


import com.example.sale.model.Custom;
import com.example.sale.model.CustomQueryVo;

import java.util.List;

public interface CustomService {


    /**
     * 查询客户列表
     * @param query
     * @return
     */
    List<Custom> queryCustom(CustomQueryVo query);

}
