package com.bancard.vpos_pagomovil_android.confirmation.view;

import com.bancard.vpos_pagomovil_android.model.OperationResponse;

public interface ConfirmationView {

    void showLoader();
    void hideLoader();
    void showErrorViewWithResponse(OperationResponse operationResponse);
    void showErrorView();
    void showSuccessView(OperationResponse operationResponse);
}
