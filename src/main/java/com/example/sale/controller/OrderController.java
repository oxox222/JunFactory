package com.example.sale.controller;

import com.example.sale.model.PageResult;
import com.example.sale.model.Tsales;
import com.example.sale.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: OrderController
 * @Description: 出货单controller
 * @Author: PANLVZ
 * @Date: 2020-03-03
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/add", produces = "application/json; charset=utf-8")
    public String addOrder(@RequestBody Tsales tsales) {
        orderService.addOrder(tsales);
        return "success";
    }

    @RequestMapping("/selectAll")
    public List<Tsales> selectAll() {
        return orderService.selectAll();
    }

    @RequestMapping("/selectByKeyword")
    public PageResult<Tsales> selectByKeyword (@RequestParam("keyword") String keyword,
                                      @RequestParam(value = "index", required = false, defaultValue = "0") int index,
                                      @RequestParam(value = "size",  required = false, defaultValue = "10") int size) throws Exception{
        List<Tsales> list = orderService.selectByKeyword(keyword, index, size);
        PageResult pageResult = new PageResult();
        pageResult.setList(list);
        pageResult.setCount(orderService.selectCountByKeyword(keyword));
        return pageResult;
    }

    @RequestMapping("/getTotalPrice")
    public double getTotalPrice(@RequestParam(value = "startTime",required = false) String startTime,
                                @RequestParam(value = "endTime", required = false) String endTime,
                                @RequestParam(value = "party", required = false) String party,
                                @RequestParam(value = "consignee", required = false) String consignee) {
        return orderService.getTotalPrice(startTime, endTime, party, consignee);
    }
}
