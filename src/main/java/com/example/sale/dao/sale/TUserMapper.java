package com.example.sale.dao.sale;

import com.example.sale.model.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectPassword(@Param("user") String user);

    void updateLastLoginTime(@Param("time") String time, @Param("user") String user);
}