package com.example.sale.service;

import com.example.sale.model.Tsales;

import java.util.List;

public interface OrderService {

    void addOrder(Tsales tsales);

    List<Tsales> selectAll();

    List<Tsales> selectByKeyword(String keyword, int index, int size);

    Integer selectCountByKeyword(String keyword);

    double getTotalPrice(String starttime, String endTime, String party, String consignee);
}
