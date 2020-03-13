package com.example.sale.service;

import com.example.sale.model.*;

import java.util.List;

public interface OrderService {

    void addOrder(Tsales tsales);

    List<Tsales> selectAll();

    List<Tsales> selectByKeyword(String keyword, int index, int size);

    Integer selectCountByKeyword(String keyword);

    double getTotalPrice(String starttime, String endTime, String party, String consignee);

    PageResult<Tparty> selectParty(String key);

    PageResult<Tconsignee> selectConsignee(String key);

    PageResult<Tproduct> selectProduct(String party, String product);

    List<Tsales> selectSales(String startTime, String endTime,
                             String party, String consignee, String product);
}
