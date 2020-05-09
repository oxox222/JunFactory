package com.example.sale.controller;

import com.example.sale.model.*;
import com.example.sale.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    /**
     * 新增订单
     * @param tsales
     * @return
     */
    @RequestMapping(value = "/add", produces = "application/json; charset=utf-8")
    public String addOrder(@RequestBody Tsales tsales) {
        orderService.addOrder(tsales);
        return "success";
    }

    /**
     * 查询订单信息
     * @return
     */
    @RequestMapping("/selectAll")
    public List<Tsales> selectAll() {
        return orderService.selectAll();
    }

    /**
     * 根据关机键查询订单信息
     * @param keyword
     * @param index
     * @param size
     * @return
     * @throws Exception
     */
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

    /**
     * 获取总销售额
     * @param startTime
     * @param endTime
     * @param party
     * @param consignee
     * @return
     */
    @RequestMapping("/getTotalPrice")
    public double getTotalPrice(@RequestParam(value = "startTime",required = false) String startTime,
                                @RequestParam(value = "endTime", required = false) String endTime,
                                @RequestParam(value = "party", required = false) String party,
                                @RequestParam(value = "consignee", required = false) String consignee) {
        return orderService.getTotalPrice(startTime, endTime, party, consignee);
    }

    /**
     * 查询甲方
     * @param key
     * @return
     */
    @RequestMapping("/selectParty")
    public PageResult<Tparty> selectParty(@RequestParam("key") String key) {
        return orderService.selectParty(key);
    }

    /**
     * 查询收货人
     * @param key
     * @return
     */
    @RequestMapping("/selectConsignee")
    public PageResult<Tconsignee> selectConsignee(@RequestParam("key") String key) {
        return orderService.selectConsignee(key);
    }

    /**
     * 查询甲方-产品信息
     * @param party
     * @param product
     * @return
     */
    @RequestMapping("/selectProduct")
    public PageResult<Tproduct> selectProduct(@RequestParam("party") String party,
                                              @RequestParam("product") String product) {
        return orderService.selectProduct(party, product);
    }

    /**
     * 查询订单
     * @param startTime
     * @param endTime
     * @param party
     * @param consignee
     * @param product
     * @return
     */
    @RequestMapping("/selectSales")
    public List<Tsales> selectSales(@RequestParam(value = "startTime",required = false) String startTime,
                                    @RequestParam(value = "endTime", required = false) String endTime,
                                    @RequestParam(value = "party", required = false) String party,
                                    @RequestParam(value = "consignee", required = false) String consignee,
                                    @RequestParam(value = "product", required = false) String product) {
        return orderService.selectSales(startTime, endTime, party, consignee, product);
    }

}
