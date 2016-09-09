package com.bancard.vpos_pagomovil_android.model;

public class OperationResponse {

    private String token;
    private String shopProcessId;
    private String response;
    private String responseDetails;
    private String amount;
    private String currency;
    private String authorizationNumber;
    private String ticketNumber;
    private String responseCode;
    private String responseDescription;
    private String extendedResponseDescription;
    private SecurityInformation securityInformation;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShopProcessId() {
        return shopProcessId;
    }

    public void setShopProcessId(String shopProcessId) {
        this.shopProcessId = shopProcessId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        this.authorizationNumber = authorizationNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getExtendedResponseDescription() {
        return extendedResponseDescription;
    }

    public void setExtendedResponseDescription(String extendedResponseDescription) {
        this.extendedResponseDescription = extendedResponseDescription;
    }

    public SecurityInformation getSecurityInformation() {
        return securityInformation;
    }

    public void setSecurityInformation(SecurityInformation securityInformation) {
        this.securityInformation = securityInformation;
    }
}
