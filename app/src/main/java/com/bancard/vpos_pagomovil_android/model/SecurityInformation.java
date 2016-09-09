package com.bancard.vpos_pagomovil_android.model;

public class SecurityInformation {

    private String customerIp;
    private String cardSource;
    private String cardCountry;
    private String version;
    private String riskIndex;

    public SecurityInformation() {}

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getCardSource() {
        return cardSource;
    }

    public void setCardSource(String cardSource) {
        this.cardSource = cardSource;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public void setCardCountry(String cardCountry) {
        this.cardCountry = cardCountry;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRiskIndex() {
        return riskIndex;
    }

    public void setRiskIndex(String riskIndex) {
        this.riskIndex = riskIndex;
    }
}
