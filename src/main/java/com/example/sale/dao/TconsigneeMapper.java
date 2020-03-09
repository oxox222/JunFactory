package com.example.sale.dao;

import com.example.sale.model.Tconsignee;

import java.util.List;

public interface TconsigneeMapper {
    int insert(Tconsignee record);

    int insertSelective(Tconsignee record);

    List<Tconsignee> selectAll();
}