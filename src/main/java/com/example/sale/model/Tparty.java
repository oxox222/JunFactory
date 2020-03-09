package com.example.sale.model;

import java.io.Serializable;

public class Tparty implements Serializable {
    private String party;

    private static final long serialVersionUID = 1L;

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party == null ? null : party.trim();
    }
}