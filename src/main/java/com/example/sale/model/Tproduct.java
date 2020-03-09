package com.example.sale.model;

import java.io.Serializable;

public class Tproduct implements Serializable {
    private String party;

    private String product;

    private static final long serialVersionUID = 1L;

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party == null ? null : party.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }
}