package com.bancard.vpos_pagomovil_android.model;

public class Payment {

    private String publicKey;
    private boolean testClient;
    private Operation operation;

    public Payment() {}

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isTestClient() {
        return testClient;
    }

    public void setTestClient(boolean testClient) {
        this.testClient = testClient;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
