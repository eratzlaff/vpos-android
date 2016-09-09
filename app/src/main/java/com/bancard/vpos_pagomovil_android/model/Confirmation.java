package com.bancard.vpos_pagomovil_android.model;

public class Confirmation {

    private long id;
    private String sentData;
    private String commerceResponse;

    public Confirmation() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSentData() {
        return sentData;
    }

    public void setSentData(String sentData) {
        this.sentData = sentData;
    }

    public String getCommerceResponse() {
        return commerceResponse;
    }

    public void setCommerceResponse(String commerceResponse) {
        this.commerceResponse = commerceResponse;
    }
}
