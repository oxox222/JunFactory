package com.example.sale.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Custom implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 负责人联系方式
     */
    private String leaderPhone;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 传真
     */
    private String fax;

    /**
     * 所属地区
     */
    private String address;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行账户
     */
    private String bankAccountName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

}