package com.bancard.vpos_pagomovil_android.payment.view;

public interface PaymentView {

    void onSuccessPayment(String processId, int processShopId);
    void onFailure();
    void showLoader();
    void hideLoader();
}
