package com.example.sale.controller;


import com.example.sale.model.Custom;
import com.example.sale.model.CustomQueryVo;
import com.example.sale.model.Result;
import com.example.sale.service.CustomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomController
 * @Description: 客户管理接口
 * @Author: PANLVZ
 * @Date: 2020/7/3
 */
@RestController
@RequestMapping("/custom")
public class CustomController {

    @Resource
    private CustomService customService;


    /**
     * 查询客户列表
     * @param query
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result queryList(CustomQueryVo query) {
        List<Custom> result = customService.queryCustom(query);
        return Result.success(result);
    }

}
