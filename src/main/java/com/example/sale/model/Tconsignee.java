package com.example.sale.model;

import java.io.Serializable;

public class Tconsignee implements Serializable {
    private String consignee;

    private static final long serialVersionUID = 1L;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }
}