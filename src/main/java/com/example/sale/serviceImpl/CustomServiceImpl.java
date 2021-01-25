package com.example.sale.serviceImpl;


import com.example.sale.dao.erp.CustomMapper;
import com.example.sale.model.Custom;
import com.example.sale.model.CustomQueryVo;
import com.example.sale.service.CustomService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomServiceImpl
 * @Description: 客户管理业务
 * @Author: PANLVZ
 * @Date: 2020/7/3
 */
@Service
public class CustomServiceImpl implements CustomService {

    @Resource
    private CustomMapper customMapper;


    @Override
    @Cacheable(value = "query")
    public List<Custom> queryCustom(CustomQueryVo query) {
        List<Custom> list = customMapper.queryList(query);
        return list;
    }

}
