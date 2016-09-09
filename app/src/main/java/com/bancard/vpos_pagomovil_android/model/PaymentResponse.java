package com.bancard.vpos_pagomovil_android.model;

public class PaymentResponse {

    private String status;
    private String processId;

    public PaymentResponse() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
