package com.bancard.vpos_pagomovil_android.model;

public class BaseConfirmationResponse {

    private String status;
    private Confirmation confirmation;

    public BaseConfirmationResponse() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }
}
